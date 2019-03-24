package com.daishuai.es.dao;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/10 17:43
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Slf4j
@Component
public class ESRepository {

    @Autowired
    private TransportClient client;

    /**
     * 创建索引
     * @param index
     * @return
     */
    public boolean createIndex(String index) {
        CreateIndexRequest request = new CreateIndexRequest(index);

        //CreateIndexResponse response = client.admin().indices().create(request).get();
        CreateIndexResponse indexResponse = client.admin().indices().prepareCreate(index).execute().actionGet();
        return indexResponse.isAcknowledged();
    }

    /**
     * 索引文档：以JSON字符串对象来表示文档
     * @param index
     * @param type
     * @param id
     * @param jsonString    JSON字符串
     */
    public void indexDocument(String index, String type, String id, String jsonString) {
        //1、创建索引请求
        IndexRequest request = new IndexRequest(index, type, id);
        //2、准备文档数据
        request.source(jsonString, XContentType.JSON);
        //3、其他的一些可选设置
        /*
            request.routing("routing");  //设置routing值
            request.timeout(TimeValue.timeValueSeconds(1));  //设置主分片等待时长
            request.setRefreshPolicy("wait_for");  //设置重刷新策略
            request.version(2);  //设置版本号
            request.opType(DocWriteRequest.OpType.CREATE);  //操作类别
        */
        //4、发送请求
        //方式一、用client.index方法，返回时ActionFuture<IndexResponse>，再调用get获取响应结果
        try {
            IndexResponse response = client.index(request).get();
        } catch (ElasticsearchException e) {
            //判断是否版本冲突，create但文档已经存在冲突
            if (e.status() == RestStatus.CONFLICT) {
                log.error("冲突了");
            }
            log.info("index document occurred exception: {}", e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException | ExecutionException e) {
            log.info("index document occurred exception: {}", e.getMessage());
            e.printStackTrace();
        }
        //方式二、client提供了一个prepareIndex方法，内部为我们创建IndexRequest
        //IndexResponse response = client.prepareIndex(index, type, id).setSource(jsonString, XContentType.JSON).get();
        //方式三、request + listener ActionListener<IndexResponse>
        /*client.index(request, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });*/
        //5、处理响应
    }

    /**
     * 索引文档：以map对象来表示文档，或者直接用key-value对给出，或者用XContentBuilder来构建文档
     * @param index
     * @param type
     * @param id
     * @param data  map对象
     */
    public void indexDocument(String index, String type, String id, Map<String, Object> data) {
        //1、创建索引请求
        IndexRequest request = new IndexRequest(index, type, id);
        //2、准备文档数据
        //2.1、以map对象来表示文档
        request.source(data);
        //2.2、直接用key-value对给出
        request.source("key1", "value1",
                       "key2", "value2",
                        "key3", "value3");
        //2.3、用XContentBuilder来构建文档
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            Set<String> set = data.keySet();
            for (String key : set) {
                builder.field(key, data.get(key));
            }
            builder.endObject();
            request.source(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3、其他的一些可选设置
        /*
            request.routing("routing");  //设置routing值
            request.timeout(TimeValue.timeValueSeconds(1));  //设置主分片等待时长
            request.setRefreshPolicy("wait_for");  //设置重刷新策略
            request.version(2);  //设置版本号
            request.opType(DocWriteRequest.OpType.CREATE);  //操作类别
        */
    }


    /**
     * 获取文档
     * @param index
     * @param type
     * @param id
     */
    public void getDocument(String index, String type, String id) {
        //1、创建获取文档请求
        GetRequest request = new GetRequest(index, type, id);
        //2、可选的配置
        request.routing("routing");
        request.version(2);
        //是否获取_source字段
        /*request.fetchSourceContext(new FetchSourceContext(false));
        String[] includes = new String[]{"",""};//要获取的字段
        String[] excludes = new String[]{"",""};//要排除的字段
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);*/
        //3、发送请求
        GetResponse getResponse = null;
        try {
            getResponse = client.get(request).get();
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                log.error("没有找到该id的文档" );
            }
            if (e.status() == RestStatus.CONFLICT) {
                log.error("获取时版本冲突了，请在此写冲突处理逻辑！" );
            }
            log.error("get document occurred exception: {}", e);
        }catch (InterruptedException | ExecutionException e) {
            log.error("get document occurred exception: {}", e);
        }
    }

    /**
     * 批量索引文档
     */
    public void batchIndexDocument() throws ExecutionException, InterruptedException {
        // 1、创建批量操作请求
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("mess", "_doc", "1")
                .source(XContentType.JSON,"field", "foo"));
        request.add(new IndexRequest("mess", "_doc", "2")
                .source(XContentType.JSON,"field", "bar"));
        request.add(new IndexRequest("mess", "_doc", "3")
                .source(XContentType.JSON,"field", "baz"));

        /*
        request.add(new DeleteRequest("mess", "_doc", "3"));
        request.add(new UpdateRequest("mess", "_doc", "2")
                .doc(XContentType.JSON,"other", "test"));
        request.add(new IndexRequest("mess", "_doc", "4")
                .source(XContentType.JSON,"field", "baz"));
        */
        // 2、可选的设置
        /*
        request.timeout("2m");
        request.setRefreshPolicy("wait_for");
        request.waitForActiveShards(2);
        */
        //3、发送请求
        // 同步请求
        BulkResponse bulkResponse = client.bulk(request).get();

        //4、处理响应
        if(bulkResponse != null) {
            for (BulkItemResponse bulkItemResponse : bulkResponse) {
                DocWriteResponse itemResponse = bulkItemResponse.getResponse();

                if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                        || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                    IndexResponse indexResponse = (IndexResponse) itemResponse;
                    //TODO 新增成功的处理

                } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                    UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                    //TODO 修改成功的处理

                } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                    DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                    //TODO 删除成功的处理
                }
            }
        }

        //异步方式发送批量操作请求
        /*
        ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        client.bulkAsync(request, listener);
        */

    }

    /**
     * 搜索文档
     * @param index
     * @param type
     */
    public void searchDocument(String index, String type) throws ExecutionException, InterruptedException {
        // 1、创建search请求
        //SearchRequest searchRequest = new SearchRequest();
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);

        // 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //构造QueryBuilder
            /*QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("user", "kimchy")
                    .fuzziness(Fuzziness.AUTO)
                    .prefixLength(3)
                    .maxExpansions(10);
            sourceBuilder.query(matchQueryBuilder);*/

        sourceBuilder.query(QueryBuilders.termQuery("age", 24));
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //是否返回_source字段
        //sourceBuilder.fetchSource(false);

        //设置返回哪些字段
        /*String[] includeFields = new String[] {"title", "user", "innerObject.*"};
        String[] excludeFields = new String[] {"_type"};
        sourceBuilder.fetchSource(includeFields, excludeFields);*/

        //指定排序
        //sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        //sourceBuilder.sort(new FieldSortBuilder("_uid").order(SortOrder.ASC));

        // 设置返回 profile
        //sourceBuilder.profile(true);

        //将请求体加入到请求中
        searchRequest.source(sourceBuilder);

        // 可选的设置
        //searchRequest.routing("routing");

        // 高亮设置
        /*HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle =
                new HighlightBuilder.Field("title");
        highlightTitle.highlighterType("unified");
        highlightBuilder.field(highlightTitle);
        HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("user");
        highlightBuilder.field(highlightUser);
        sourceBuilder.highlighter(highlightBuilder);*/


        //加入聚合
            /*TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_company")
                    .field("company.keyword");
            aggregation.subAggregation(AggregationBuilders.avg("average_age")
                    .field("age"));
            sourceBuilder.aggregation(aggregation);*/

        //做查询建议
            /*SuggestionBuilder termSuggestionBuilder =
                    SuggestBuilders.termSuggestion("user").text("kmichy");
                SuggestBuilder suggestBuilder = new SuggestBuilder();
                suggestBuilder.addSuggestion("suggest_user", termSuggestionBuilder);
            sourceBuilder.suggest(suggestBuilder);*/

        //3、发送请求
        SearchResponse searchResponse = client.search(searchRequest).get();


        //4、处理响应
        //搜索结果状态信息
        RestStatus status = searchResponse.status();
        TimeValue took = searchResponse.getTook();
        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
        boolean timedOut = searchResponse.isTimedOut();

        //分片搜索情况
        int totalShards = searchResponse.getTotalShards();
        int successfulShards = searchResponse.getSuccessfulShards();
        int failedShards = searchResponse.getFailedShards();
        for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
            // failures should be handled here
        }

        //处理搜索命中文档结果
        SearchHits hits = searchResponse.getHits();

        long totalHits = hits.getTotalHits();
        float maxScore = hits.getMaxScore();

        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            // do something with the SearchHit

            String index1 = hit.getIndex();
            String type1 = hit.getType();
            String id = hit.getId();
            float score = hit.getScore();

            //取_source字段值
            String sourceAsString = hit.getSourceAsString(); //取成json串
            Map<String, Object> sourceAsMap = hit.getSourceAsMap(); // 取成map对象
            //从map中取字段值
                /*
                String documentTitle = (String) sourceAsMap.get("title");
                List<Object> users = (List<Object>) sourceAsMap.get("user");
                Map<String, Object> innerObject = (Map<String, Object>) sourceAsMap.get("innerObject");
                */
            log.info("index:" + index + "  type:" + type + "  id:" + id);
            log.info(sourceAsString);

            //取高亮结果
                /*Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField highlight = highlightFields.get("title");
                Text[] fragments = highlight.fragments();
                String fragmentString = fragments[0].string();*/
        }

        // 获取聚合结果
            /*
            Aggregations aggregations = searchResponse.getAggregations();
            Terms byCompanyAggregation = aggregations.get("by_company");
            Bucket elasticBucket = byCompanyAggregation.getBucketByKey("Elastic");
            Avg averageAge = elasticBucket.getAggregations().get("average_age");
            double avg = averageAge.getValue();
            */

        // 获取建议结果
            /*Suggest suggest = searchResponse.getSuggest();
            TermSuggestion termSuggestion = suggest.getSuggestion("suggest_user");
            for (TermSuggestion.Entry entry : termSuggestion.getEntries()) {
                for (TermSuggestion.Entry.Option option : entry) {
                    String suggestText = option.getText().string();
                }
            }
            */

        //异步方式发送获查询请求
            /*
            ActionListener<SearchResponse> listener = new ActionListener<SearchResponse>() {
                @Override
                public void onResponse(SearchResponse getResponse) {
                    //结果获取
                }

                @Override
                public void onFailure(Exception e) {
                    //失败处理
                }
            };
            client.searchAsync(searchRequest, listener);
            */
    }


}

package com.daishuai.elasticsearch;

import com.daishuai.elasticsearch.entity.Article;
import com.daishuai.elasticsearch.entity.Book;
import com.daishuai.elasticsearch.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private BookRepository bookRepository;

	@Test
	public void contextLoads() {
        Article article = new Article();
        article.setId(1);
        article.setAuthor("张三");
        article.setTitle("德国小组未出线");
        article.setContent("2018年6月27号晚上十点，2018年俄罗斯世界杯小组赛第三轮继续进行，F组之中，在喀山体育场，德国与韩国激战，上半场0:0，下半场一开始，德国开始进攻，导致后防无人，韩国几次攻向后方，最后几分钟，韩国角球，最后时刻，金英权门前绝杀，1:0领先，最后孙兴慜推射空门，最终，韩国2:0德国。" +
                "小组赛战罢，德国1胜2负拿到3分，仅列小组第四，惨遭淘汰。" +
                "韩国队1胜2负拿到3分，也被淘汰。" +
                "比赛第91分钟，平局从此改写，孙兴慜左侧角球传入禁区，克罗斯解围时踢向自家球门，门前无人盯防的金英权轻松破门。主裁判虽然先判了越位在先，但经过视频裁判回放，判定进球有效，1:0。" );
        Index index = new Index.Builder(article).index("news").type("sports").build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getArticle(){
	    String query = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"德国\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search search = new Search.Builder(query).addIndex("news").addType("sports").build();
        try {
            SearchResult result = jestClient.execute(search);
            String jsonString = result.getJsonString();
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add(){
        List<IndexQuery> books = new ArrayList<IndexQuery>();
        Book book1 = new Book();
        book1.setId(10);
        book1.setAuthor("吴承恩");
        book1.setBookName("西游记");
        IndexQuery indexQuery1 = new IndexQueryBuilder().withObject(book1).build();
        books.add(indexQuery1);
        Book book2 = new Book();
        book2.setId(20);
        book2.setAuthor("罗贯中");
        book2.setBookName("三国演义");
        IndexQuery indexQuery2 = new IndexQueryBuilder().withObject(book2).build();
        books.add(indexQuery2);
        Book book3 = new Book();
        book3.setId(30);
        book3.setAuthor("曹雪芹");
        book3.setBookName("红楼梦");
        IndexQuery indexQuery3 = new IndexQueryBuilder().withObject(book3).build();
        books.add(indexQuery3);
        Book book4 = new Book();
        book4.setId(40);
        book4.setAuthor("施耐庵");
        book4.setBookName("水浒传");
        IndexQuery indexQuery4 = new IndexQueryBuilder().withObject(book4).build();
        books.add(indexQuery4);
        elasticsearchTemplate.bulkIndex(books);
    }

    @Test
    public void get(){

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("西")).build();
        List<Book> books = elasticsearchTemplate.queryForList(searchQuery, Book.class);
        System.out.println(books.get(0).getAuthor());
    }

    @Test
    public void addRepository(){
        Book book = new Book();
        book.setId(101);
        book.setAuthor("飞龙在天");
        book.setBookName("金麟岂是池中物，一遇风云便化龙！！！");
        //bookRepository.index(book);
        bookRepository.save(book);
    }

    @Test
    public void getRepository(){
        Book book = bookRepository.findOne(101);
        System.out.println(book);
    }
}

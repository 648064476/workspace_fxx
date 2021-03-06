package com.fxx.es;

import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping
public class EsController {

    @Autowired(required = false)
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired(required = false)
    private ItemRepository itemRepository;

    @Autowired(required = false)
    private BookRepository bookRepository;


    /**
     * 创建索引
     */
    @PostMapping("createIndex")
    public String createIndex () {
        elasticsearchTemplate.createIndex(Item.class);
        return "成功";
    }

    /**
     * 删除索引
     */
    @PostMapping("deleteIndex")
    public String deleteIndex () {
        elasticsearchTemplate.deleteIndex(Item.class);
        return "成功";

    }

    /**
     * 新增数据
     */
    @PostMapping("insert")
    public String insert () {
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        itemRepository.save(item);
        return "成功";

    }


    /**
     * 新增数据
     */
    @PostMapping("insertList")
    public String insertList () {
        List<Item> list = new ArrayList<>();
//        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
//        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.baidu.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
        return "成功";

    }

    /**
     * 新增数据
     */
    @PostMapping("update")
    public String update () {
        Item item = new Item(1L, "苹果XSMax", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        itemRepository.save(item);
        return "成功";
    }


    /**
     * 查询数据
     */
    @GetMapping("queryAll")
    public Iterable queryAll () {
        // 查找所有
        //Iterable<Item> list = this.itemRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<Item> list = this.itemRepository.findAll(Sort.by("price").ascending());
        for (Item item : list) {
            System.out.println(item);
        }
        return list;
    }

    /**
     * 区间 查询数据
     */
    @GetMapping("queryByPriceBetween")
    public List queryByPriceBetween () {
        List<Item> list = this.itemRepository.findByPriceBetween(2000.00, 3500.00);
        for (Item item : list) {
            System.out.println("item = " + item);
        }
        return list;
    }

    /**
     * 自定义查询
     */
    @GetMapping("testMatchQuery")
    public Page<Item> testMatchQuery () {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "小米手机"));
        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("total = " + total);
        for (Item item : items) {
            System.out.println(item);
        }
        return items;
    }


    /**
     * @Description:分页查询
     */
    @GetMapping("searchByPage")
    public Page searchByPage () {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("category", "手机"));
        // 分页：
        int page = 0;
        int size = 2;
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("总条数 = " + total);
        // 总页数
        System.out.println("总页数 = " + items.getTotalPages());
        // 当前页
        System.out.println("当前页：" + items.getNumber());
        // 每页大小
        System.out.println("每页大小：" + items.getSize());

        for (Item item : items) {
            System.out.println(item);
        }
        return items;
    }

    /**
     * @Description:排序查询
     */
    @GetMapping("searchAndSort")
    public Page searchAndSort () {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("category", "手机"));

        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));

        // 搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 总条数
        long total = items.getTotalElements();
        System.out.println("总条数 = " + total);

        for (Item item : items) {
            System.out.println(item);
        }
        return items;
    }


    /**
     * @Description:按照品牌brand进行分组
     */
    @GetMapping("testAgg")
    public List<StringTerms.Bucket> testAgg () {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));


        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
//        queryBuilder.addAggregation(
//                AggregationBuilders.terms("brands").field("brand"));

//        1）统计某个字段的数量
        queryBuilder.addAggregation(AggregationBuilders.count("count_uid").field("brand"));
//（2）去重统计某个字段的数量（有少量误差）
//        queryBuilder.addAggregation(AggregationBuilders.cardinality("distinct_count_uid").field("uid"));
//（3）聚合过滤
//        queryBuilder.addAggregation(AggregationBuilders.filter("uid_filter",QueryBuilders.queryStringQuery("uid:001")));
//（4）按某个字段分组
//        queryBuilder.addAggregation(AggregationBuilders.terms("group_name").field("name"));
//（5）求和
//        queryBuilder.addAggregation(AggregationBuilders.sum("sum_price").field("price"));
//（6）求平均
//        queryBuilder.addAggregation(AggregationBuilders.avg("avg_price").field("price"));
//（7）求最大值
//        queryBuilder.addAggregation(AggregationBuilders.max("max_price").field("price"));
//（8）求最小值
//        queryBuilder.addAggregation(AggregationBuilders.min("min_price").field("price"));
//（9）按日期间隔分组
//        queryBuilder.addAggregation(AggregationBuilders.dateHistogram("dh").field("date"));
//（10）获取聚合里面的结果
//        queryBuilder.addAggregation(AggregationBuilders.topHits("top_result"));
//（11）嵌套的聚合
//        queryBuilder.addAggregation(AggregationBuilders.nested("negsted_path","quests"));
//（12）反转嵌套
//        queryBuilder.addAggregation(AggregationBuilders.reverseNested("res_negsted").path("kps "));


        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("count_uid");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            // 3.5、获取桶中的文档数量
            System.out.println(bucket.getDocCount());
        }
        return buckets;
    }

    /**
     * @Description:按照品牌brand进行分组
     */
    @GetMapping("testSubAgg")
    public void testSubAgg () {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(
                AggregationBuilders.terms("brands").field("brand")
                        .subAggregation(AggregationBuilders.avg("priceAvg").field("price")) // 在品牌聚合桶内进行嵌套聚合，求平均值
        );
        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称  3.5、获取桶中的文档数量
            System.out.println(bucket.getKeyAsString() + "，共" + bucket.getDocCount() + "台");

            // 3.6.获取子聚合结果：
            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("priceAvg");
            System.out.println("平均售价：" + avg.getValue());
        }
    }


    /**
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     */
    @RequestMapping("/singleTitle")
    public Object singleTitle (String word, @PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(word)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Book.class);
    }

    /**
     * 单字符串模糊查询，单字段排序。
     *
     * @param word
     * @param pageable
     * @return
     */
    @RequestMapping("/singlePost")
    public Object singlePost (String word, @PageableDefault(sort = "weight", direction = Sort.Direction.DESC) Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(word)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Book.class);
    }


    /**
     * 某字段按字符串模糊查询
     */
    @RequestMapping("/singleMatch")
    public Object singleMatch (String content, Integer userId, @PageableDefault Pageable pageable) {
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("content", content)).withPageable(pageable).build();
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("userId", userId)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Book.class);
    }


    /**
     * term匹配，即不分词匹配，你传来什么值就会拿你传的值去做完全匹配
     */
    @RequestMapping("/singleTerm")
    public Object singleTerm (Integer userId, @PageableDefault Pageable pageable) {
        //不对传来的值分词，去找完全匹配的
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery("userId", userId)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Book.class);
    }

    /**
     * 单字段对某短语进行匹配查询，短语分词的顺序会影响结果
     */
    @RequestMapping("/singlePhraseMatch")
    public Object singlePhraseMatch (String content, @PageableDefault Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("content", content)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Book.class);
    }


    /**
     * 多字段匹配
     */
    @RequestMapping("/multiMatch")
    public Object multiMatch (String title, @PageableDefault(sort = "weight", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.multiMatchQuery(title, "title", "content")).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Book.class);
    }


    /**
     * 单字段包含所有输入
     */
    @RequestMapping("/contain")
    public Object contain (String title) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("title", title).operator(Operator.AND)).build();
        return elasticsearchTemplate.queryForList(searchQuery, Book.class);
    }


    /**
     * 单字段包含所有输入(按比例包含)
     */
    @RequestMapping("/contain1")
    public Object contain1 (String title) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("title", title).operator(Operator.AND).minimumShouldMatch("75%")).build();
        return elasticsearchTemplate.queryForList(searchQuery, Book.class);
    }

    /**
     * 多字段合并查询
     */
    @RequestMapping("/bool")
    public Object bool(String title, Integer userId, Integer weight) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userId", userId))
                .should(QueryBuilders.rangeQuery("weight").lt(weight)).must(QueryBuilders.matchQuery("title", title))).build();
        return elasticsearchTemplate.queryForList(searchQuery, Book.class);
    }
}

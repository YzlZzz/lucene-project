package com.cufe.lucene;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.demo.knn.DemoEmbeddings;
import org.apache.lucene.demo.knn.KnnVectorDict;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SearchFile {

    public static Document[] search(String queryString, String field) throws Exception {
        String index = "E:\\CUFE\\Information Retrieval\\IR system\\lucene-project\\index";
        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new StandardAnalyzer();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        QueryParser parser = new QueryParser(field, analyzer);

        String line = queryString;
        line = line.trim();

        Query query = parser.parse(line);

        String var = query.toString(field);

        TopDocs results = searcher.search(query, 50);
        ScoreDoc[] hits = results.scoreDocs;

        int numTotalHits = Math.toIntExact(results.totalHits.value);
        System.out.println(numTotalHits);
        int pageSize = (numTotalHits > 10) ? 10 : numTotalHits;

        StoredFields storedFields = searcher.storedFields();

        Document[] docs = new Document[pageSize];


        for (int i = 0; i < pageSize; i++) {
            docs[i] = storedFields.document(hits[i].doc);
        }

        return docs;
    }
    public static String insertString(String string, String insert){
        String[] split = string.split(" ");
        String ans = "";

        for (String s : split) {
            ans = insert + s;
        }
        return ans;
    }
}

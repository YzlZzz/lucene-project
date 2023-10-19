package com.cufe.lucene;// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.demo.knn.DemoEmbeddings;
import org.apache.lucene.demo.knn.KnnVectorDict;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.KeywordField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class IndexFile implements AutoCloseable {
    static final String KNN_DICT = "knn-dict";
    private final DemoEmbeddings demoEmbeddings;
    private final KnnVectorDict vectorDict;

    private IndexFile(KnnVectorDict vectorDict) throws IOException {
        if (vectorDict != null) {
            this.vectorDict = vectorDict;
            this.demoEmbeddings = new DemoEmbeddings(vectorDict);
        } else {
            this.vectorDict = null;
            this.demoEmbeddings = null;
        }

    }

    public static void main(String[] args) throws Exception {

        String indexPath = "index";
        String docsPath = "E:\\CUFE\\Information Retrieval\\IR system\\lucene\\metaData\\fullText";
        String vectorDictSource = null;
        boolean create = true;

        for(int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "-index":
                    ++i;
                    indexPath = args[i];
                    break;
                case "-docs":
                    ++i;
                    docsPath = args[i];
                    break;
                case "-knn_dict":
                    ++i;
                    vectorDictSource = args[i];
                    break;
                case "-update":
                    create = false;
                    break;
                case "-create":
                    create = true;
                    break;
                default:
                    throw new IllegalArgumentException("unknown parameter " + args[i]);
            }
        }



        Path docDir = Paths.get(docsPath);
        if (!Files.isReadable(docDir)) {
            System.out.println("Document directory '" + docDir.toAbsolutePath() + "' does not exist or is not readable, please check the path");
            System.exit(1);
        }

        Date start = new Date();

        PrintStream var10000;
        try {
            System.out.println("Indexing to directory '" + indexPath + "'...");
            Directory dir = FSDirectory.open(Paths.get(indexPath));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            if (create) {
                iwc.setOpenMode(OpenMode.CREATE);
            } else {
                iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            }

            KnnVectorDict vectorDictInstance = null;
            long vectorDictSize = 0L;
            if (vectorDictSource != null) {
                KnnVectorDict.build(Paths.get(vectorDictSource), dir, "knn-dict");
                vectorDictInstance = new KnnVectorDict(dir, "knn-dict");
                vectorDictSize = vectorDictInstance.ramBytesUsed();
            }

            try {
                IndexWriter writer = new IndexWriter(dir, iwc);

                try {
                    IndexFile indexFiles = new IndexFile(vectorDictInstance);

                    try {
                        indexFiles.indexDocs(writer, docDir);
                    } catch (Throwable var30) {
                        try {
                            indexFiles.close();
                        } catch (Throwable var29) {
                            var30.addSuppressed(var29);
                        }

                        throw var30;
                    }

                    indexFiles.close();
                } catch (Throwable var31) {
                    try {
                        writer.close();
                    } catch (Throwable var28) {
                        var31.addSuppressed(var28);
                    }

                    throw var31;
                }

                writer.close();
            } finally {
                IOUtils.close(new Closeable[]{vectorDictInstance});
            }

            Date end = new Date();
            IndexReader reader = DirectoryReader.open(dir);

            if (reader != null) {
                reader.close();
            }
        } catch (IOException var34) {
            var10000 = System.out;
            Class var10001 = var34.getClass();
            var10000.println(" caught a " + var10001 + "\n with message: " + var34.getMessage());
        }

    }

    void indexDocs(final IndexWriter writer, Path path) throws IOException {
        if (Files.isDirectory(path, new LinkOption[0])) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    try {
                        IndexFile.this.indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
                    } catch (IOException var4) {
                        var4.printStackTrace(System.err);
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            this.indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
        }

    }

    void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException {
        InputStream stream = Files.newInputStream(file);

        try {
            Document doc = new Document();
            doc.add(new KeywordField("path", file.toString(), Store.YES));

            doc.add(new LongField("modified", lastModified, Store.NO));
            doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));


            if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
                System.out.println("adding " + file);
                writer.addDocument(doc);
            } else {
                System.out.println("updating " + file);
                writer.updateDocument(new Term("path", file.toString()), doc);
            }
        } catch (Throwable var13) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Throwable var10) {
                    var13.addSuppressed(var10);
                }
            }

            throw var13;
        }

        if (stream != null) {
            stream.close();
        }

    }

    public void close() throws IOException {
        IOUtils.close(new Closeable[]{this.vectorDict});
    }
}

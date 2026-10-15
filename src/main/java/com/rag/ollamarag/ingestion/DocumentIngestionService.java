
package com.rag.ollamarag.ingestion;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class DocumentIngestionService implements CommandLineRunner {

    @Value("classpath:pdf/spring-boot-reference.pdf")
    private Resource resource;

    private final VectorStore vectorStore;

    public DocumentIngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1. Ler o PDF
        TikaDocumentReader reader =
                new TikaDocumentReader(resource);

        List<Document> pdfDocuments = reader.read();

        System.out.println("PDF carregado com sucesso!");

        // 2. Dividir o conteúdo em chunks
        TextSplitter textSplitter = new TokenTextSplitter();

        List<Document> documents =
                textSplitter.split(pdfDocuments);

        System.out.println(
                "Chunks gerados: " + documents.size()
        );

        // 3. Armazenar no banco vetorial
        vectorStore.add(documents);

        System.out.println(
                "Documentos armazenados no PGvector!"
        );
    }
}

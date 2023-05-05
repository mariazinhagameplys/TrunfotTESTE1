package br.sc.senai.TrunfoFinalSpring.Controller;

import br.sc.senai.TrunfoFinalSpring.Model.Entity.Card;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("/aws")
public class AWSController {

    @GetMapping("/awsGet/{bucketName}/{keyName}")
    public ResponseEntity<URL> getObjectBites(@PathVariable String bucketName, String keyName) {

        try {
            URL url = null;
            //arquivo-teste-s3.txt
            String acessKey = "AKIA5RRHCKYZVDZ74N43";
            String acessSecretKey = "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";
            GetObjectRequest objectRequest = new GetObjectRequest(bucketName, keyName);
            AmazonS3Client client = new AmazonS3Client();


            BasicAWSCredentials credencial = new BasicAWSCredentials(acessKey, acessSecretKey);

            client = (AmazonS3Client) AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .withCredentials(new AWSStaticCredentialsProvider(credencial))
                    .build();
            if (client.doesBucketExist(bucketName)) {
                url =  client.generatePresignedUrl(bucketName, keyName, DateTime.now().plusHours(24).toDate());
            }
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/post/{bucketName}/{keyName}")
    public ResponseEntity<String> put(@PathVariable String bucketName, @PathVariable String keyName){
        try {
            File file = new File("C:/Users/maria_cb_farias/Downloads/maria.txt");
            PutObjectRequest objectRequest = new PutObjectRequest(bucketName,keyName, file);
            String acessKey = "AKIA5RRHCKYZVDZ74N43";
            String acessSecretKey = "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";
            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(acessKey,acessSecretKey);
            AmazonS3Client s3 = new AmazonS3Client(basicAWSCredentials);
            if (s3.doesBucketExist(bucketName)) {
                s3.putObject(objectRequest);
            }
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("eba!");
    }
//    @PostMapping("/awsGet/{bucketromario}/{keyName}")
//    public ResponseEntity<URL> PostObjectBites(@PathVariable String bucketromario, String keyName,
//                                               @RequestBody String arquivo) {
//
//        try {
//            URL url = null;
//            //arquivo-teste-s3.txt
//            String acessKey = "AKIA5RRHCKYZVDZ74N43";
//            String acessSecretKey = "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";
//            GetObjectRequest objectRequest = new GetObjectRequest(bucketromario, keyName);
//            AmazonS3Client client = new AmazonS3Client();
//
//
//            BasicAWSCredentials credencial = new BasicAWSCredentials(acessKey, acessSecretKey);
//
//            client = (AmazonS3Client) AmazonS3ClientBuilder.standard()
//                    .withRegion(Regions.US_EAST_1)
//                    .withCredentials(new AWSStaticCredentialsProvider(credencial))
//                    .build();
//            if (client.doesBucketExist(bucketromario)) {
//                url =  client.generatePresignedUrl(bucketromario, keyName, DateTime.now().plusHours(24).toDate());
//            }
//            return ResponseEntity.ok(url);
//        } catch (Exception e) {
//            return null;
//        }
//    }


}


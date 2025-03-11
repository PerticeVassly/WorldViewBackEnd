package org.interaction.interactionbackend.util;

import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.obs.services.model.PutObjectRequest;
import java.io.InputStream;


@Component
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("huaweiyun.oss")
public class HuaweiyunObs implements CloudStorageUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public String upload(String objectName, InputStream inputStream) {
        ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endpoint);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
        PutObjectResult result = obsClient.putObject(putObjectRequest);
        return result.getObjectUrl();
    }
}

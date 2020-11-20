package org.example;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import com.google.api.gax.paging.Page;

import java.io.FileInputStream;

public class CreateBucketWithStorageClassAndLocation {

    public void deleteBucketContents(Storage theStore , String prj, String bckt) throws Exception
    {

        Bucket bucket =  theStore.get(bckt, Storage.BucketGetOption.fields(Storage.BucketField.values()));

        Page<Blob> page = bucket.list();

        for (Blob blob : page.iterateAll())
        {
            blob.delete();
        }
    }

    public static void main(String[] args) throws Exception {

        CreateBucketWithStorageClassAndLocation csb = new CreateBucketWithStorageClassAndLocation();

        String projectId =   "<your project>";
        String bucketName = "<your bucket>";
        String SERVICE_ACCOUNT_JSON_PATH = "<your-service account>";

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(SERVICE_ACCOUNT_JSON_PATH)))
                .build()
                .getService();

        csb.deleteBucketContents(storage, projectId, bucketName);
    }
}

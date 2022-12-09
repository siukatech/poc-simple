package com.siukatech.pocsimple.uuid;

import java.util.UUID;

public class Generator {
    public static void main(String... args) {
        byte[] uuidNamespaceBytes = new byte[] {79, 28, 93, 56, 11, 123};

        for (int i=0; i<100; i++){
            UUID uuidv4 = UUID.randomUUID();
            //
            uuidNamespaceBytes = ("uuid_generator_" + String.valueOf(i)).getBytes();
            uuidNamespaceBytes = (String.valueOf(i)).getBytes();
            UUID uuidv3 = UUID.nameUUIDFromBytes(uuidNamespaceBytes);
            System.out.println("uuid - v4: [" + uuidv4.toString() + "], uuid - v3: [" + uuidv3.toString() + "]");
        }
    }
}


//uuid - v4: [74a91f34-6f1b-4ffa-9259-6487e7ecb8ef], uuid - v3: [c4d87d20-f750-3d89-bda5-99e259b96aca]
//uuid - v4: [a8aed9fe-d67f-434b-b01e-90bd0851eb76], uuid - v3: [997eda75-4f2a-3385-b453-4b2bd0e0d3d1]
//uuid - v4: [05470577-42cd-4c5b-bfcc-c25a040620f3], uuid - v3: [a84d4267-bab3-3510-9323-2e7425ab293d]
//uuid - v4: [cc445b75-e442-4362-9acb-ca3a2f61036a], uuid - v3: [1b7c3460-fab3-30cc-9929-46331ee6f5d6]
//uuid - v4: [c13f8636-6421-4ddc-80f7-a9a7909ef3ad], uuid - v3: [85178fd3-9bb7-3e35-99a2-5c211ec22229]
//uuid - v4: [f4bf24ea-4d71-4b99-99df-faad232424d3], uuid - v3: [c404bb5c-5a26-3b20-be83-4e3e48028a8b]

//uuid - v4: [61438f51-99de-41d8-a0da-5c5f93141b16], uuid - v3: [c4d87d20-f750-3d89-bda5-99e259b96aca]
//uuid - v4: [5a8f4c69-f3fb-4fe8-b18e-fcde9eb0417e], uuid - v3: [997eda75-4f2a-3385-b453-4b2bd0e0d3d1]
//uuid - v4: [3210cfa4-1bcc-4d76-b9c4-ace2d6f0101d], uuid - v3: [a84d4267-bab3-3510-9323-2e7425ab293d]
//uuid - v4: [c292ac05-7b84-4e22-a87d-341c3cdbb46b], uuid - v3: [1b7c3460-fab3-30cc-9929-46331ee6f5d6]
//uuid - v4: [10d0d611-e863-47dc-85ca-c20f2b6af9e7], uuid - v3: [85178fd3-9bb7-3e35-99a2-5c211ec22229]
//uuid - v4: [030c04e2-2de8-4982-a2b4-b4d7c1181c93], uuid - v3: [c404bb5c-5a26-3b20-be83-4e3e48028a8b]

//uuid - v4: [e4acdef1-f7fe-4430-acd7-fe6e002e1b09], uuid - v3: [cfcd2084-95d5-35ef-a6e7-dff9f98764da]
//uuid - v4: [3a8ea31e-cbbb-4783-9dd9-c15626877aac], uuid - v3: [c4ca4238-a0b9-3382-8dcc-509a6f75849b]
//uuid - v4: [89d8b461-0852-473c-92d8-719018d6270b], uuid - v3: [c81e728d-9d4c-3f63-af06-7f89cc14862c]
//uuid - v4: [7a71fb0b-1c95-4e33-ae2c-04a9571fb812], uuid - v3: [eccbc87e-4b5c-32fe-a830-8fd9f2a7baf3]
//uuid - v4: [60f9c822-00ce-4d2c-8c39-a58e509c5873], uuid - v3: [a87ff679-a2f3-371d-9181-a67b7542122c]
//uuid - v4: [bb53d2e5-6460-44db-935c-d350ccab50d0], uuid - v3: [e4da3b7f-bbce-3345-9777-2b0674a318d5]

//uuid - v4: [45d24320-04e0-470f-b49e-aad41b55b06f], uuid - v3: [cfcd2084-95d5-35ef-a6e7-dff9f98764da]
//uuid - v4: [13d1aecc-e614-4fff-a0ae-34814a0f3981], uuid - v3: [c4ca4238-a0b9-3382-8dcc-509a6f75849b]
//uuid - v4: [369f57e8-f4bf-4702-ad9f-f13a27099a86], uuid - v3: [c81e728d-9d4c-3f63-af06-7f89cc14862c]
//uuid - v4: [7a2c3029-0623-4c8c-9f75-a3aab3548d5e], uuid - v3: [eccbc87e-4b5c-32fe-a830-8fd9f2a7baf3]
//uuid - v4: [3be4cbc9-3389-418b-8c8d-4d47cf1f4f22], uuid - v3: [a87ff679-a2f3-371d-9181-a67b7542122c]
//uuid - v4: [5d52d574-23a2-4bcb-809d-6d8582b31b23], uuid - v3: [e4da3b7f-bbce-3345-9777-2b0674a318d5]


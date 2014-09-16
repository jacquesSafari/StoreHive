package com.store.hive.model.people;

/**
 * Created by tinashe
 */
public enum StoreOwnerEnum {
        name {
            @Override
            public String getKey() {
                return "name";
            }
        },
        surname {
            @Override
            public String getKey() {
                return "surname";
            }
        },
        username {
            @Override
            public String getKey() {
                return "username";
            }
        },
        password {
            @Override
            public String getKey() {
                return "password";
            }
        },
        deviceId {
            @Override
            public String getKey() {
                return "deviceId";
            }
        };

    abstract public String getKey();
}

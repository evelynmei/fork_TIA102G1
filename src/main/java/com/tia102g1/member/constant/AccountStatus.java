    package com.tia102g1.member.constant;


    public enum AccountStatus {
        //目前只能設定0、1，用1、2會出現hibernate索引越界的問題
        NORMAL(0), BLOCKED(1);

        private final int status;

        AccountStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        //此方法是可以透過帳號狀態代碼取得enum相對應的值 (NORMAL、BLOCKED)
        //如果資料庫是用var類型，可以直接用enum中valueOf()就可以，不需要自己建立這個方法
        public static AccountStatus getStatus(int status) {
            for (AccountStatus accountStatus : AccountStatus.values()) {
                if (accountStatus.getStatus() == status) {
                    return accountStatus;
                }
            }
            throw new IllegalArgumentException("Unknown status: " + status);
        }

    }

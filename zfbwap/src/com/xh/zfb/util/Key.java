package com.xh.zfb.util;

public class Key {
		// 商户appid
		public static String APPID = "2016051201393971";
		// 私钥 pkcs8格式的
		public static String RSA_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDsHRoSkQs7SO5TPXwJWFKhFf43D5CdrlnlfHAW+fbbuvwXKbZaekMS4smm1+EbUE9UYLaa+ut8eLUkvNFu/PtLKy/5lrkeE4PAjkvsmVr+UUQF5QZ6Ue+R9Nj3nSAXawzVecKv+pedk4sNavPoeZze9nhiwRIa/mYYtGu15gmMxZ41bx7mB5B+NZhEzyQp+X054Upk6xBV5Q4bmOe2NCqVWCluWR4wueEv+WhghrbDAL4l69UzjPDNYPfWSGuJ5v2dac5jAuIyeAvzB1YvfmslJxvu7hS8bHVjSr/Z5S9pXWyWtZXYOiiMK17X55AMC/5yGQoHNFMS9XpkD6h/l55pAgMBAAECggEAOoBja2JAtCm30ywSxYX6wyrd1+qlXExMvc19QFQi1AKaX2rYYcaVQ+KpMAuhHNFjNnbYB8JQAoEt8LJP+f34jYFf26l6qHcRV+5p5W+B2i1se0Fpv3j5sOJHt+JYhzTg8yu+Dzg5xLM/6s0rLdikA6Jx5ZYM1F9gEJLzdRhQGW6LkWbEQwBOE/Li2iHNS7+C9SdUYGSfLjgO7LYAh3DpIe/YD62gWEwn58NFsHX9NxE9n5vJn+NJL61WL73qJk9TjUq5xx5tz/hBLV7CElYy6IdQJfgyucQjY3DxHrAviJAoGJfyXQaJG50SpkoMwALz5R0e9dYW6P1uPfMqRHciAQKBgQD520fdyFf64opQ/3Rsxukguw+JKYbUZWpAz/VbOsbO494xHdDErpgZ1CCJYNNRYw8Z/TAopW8CDB9HU4dEt+XgtMhG/53p6QWyYTHbeyR+vDJgc9IO4LCeWd71dJINscDqXwBo58TlXbc6KvzcmvklYcSBsgvRxMW4XefTBuiQKBgQDx61ER2sy7ycCMl2tPh/FCuV3En7mvY9LRwG54QvdbBNzp4WT0zpknGt76/h0IEUxxwkpJVJgisYpnrx8gutaGdViH9RVuXAPXo1u0oHMdJxr8QK60xCe39BzCkoffpmGzftJmRM67sHpSTtk2er14NcsgJkBQlotnrobSXvS44QKBgQDV1S00SfnB5Y+qsnXZgoKSWIXTQlFz0lGYZN4ayCX1M2TR4juvio2yOhN0KBUDSKOtT50Q4eOzc98VHzoTDrRted8BpXaPyk66DMgRJCXKlnLXmPwo/L29BMNW3zc8hrn+0SqCuZnIGOKx5c0d2Gj7samwmqVc53wIfWucdle0wQKBgQDIS7G6m7KJBHjErpWtqntJaFpHcot1uKuS+MU+EwvjszoI9pH00p0hMHFP1FdLF694PInuhbOS3DvM2D9s2pU7zyDCwrTEnuHxXNjk7g40DHe/JqPkI4Xwf03QdcV9BqoeUnwW59HZeZUCktnyACSF+eha0YTBytwmVkBjSOYQKBgQCd0QCPVi4aa4UQA7hWDYRHAVBWsMcEFH0VnBiRjyS6cHPLBTXfsuR29axe9O8R+SVyw8NNkrr92LD5oHQJvneKUdwtFbOoIfhcxx8r1kWOsZEaIkG5SEN0pU+vnCF4Ox2TL+ShrGuIXsImaN96KeWdJjyo08WqNL752sC6rT4WMA==";
		// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String notify_url = "https://api.ctv-cloud.com/zfbwap/notify";
		// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
		public static String return_url = "https://api.ctv-cloud.com/zfbwap/returnurl";
		// 请求网关地址
		public static String URL = "https://openapi.alipay.com/gateway.do";
		// 编码
		public static String CHARSET = "UTF-8";
		// 返回格式
		public static String FORMAT = "json";
		// 支付宝公钥
		public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7B0aEpELO0juUz18CVhSoRX+Nw+Qna5Z5XxwFvn227r8Fym2WnpDEuLJptfhG1BPVGC2mvrrfHi1JLzRbvz7Sysv+Za5HhODwI5L7Jla/lFEBeUGelHvkfTY950gF2sM1XnCr/qXnZOLDWrz6Hmc3vZ4YsESGv5mGLRrteYJjMWeNW8e5geQfjWYRM8kKfl9OeFKZOsQVeUOG5jntjQqlVgpblkeMLnhL/loYIa2wwC+JevVM4zwzWD31khrieb9nWnOYwLiMngL8wdWL35rJScb7u4UvGx1Y0q/2eUvaV1slrWV2DoojCte1+eQDAv+chkKBzRTEvV6ZA+of5eeaQIDAQAB";
		// 日志记录目录
		public static String log_path = "/log";
		// RSA2
		public static String SIGNTYPE = "RSA2";
}

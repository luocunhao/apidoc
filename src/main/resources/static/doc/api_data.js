define({ "api": [
  {
    "type": "GET",
    "url": "/hengda/getChannel",
    "title": "获取顾客渠道",
    "name": "getChannel",
    "group": "ConsultantControl",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "ReturnMsg",
            "description": "<p>0：发送成功 1：发送失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回示例",
          "content": "{\n    \"code\":\"0\",\n    \"content\":\n    [\n\n         {\n            'id':1,'channel':'渠道A'\n          }\n\n    ],\n    \"errorMsg\":null\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/ideagit/apidoc/src/main/java/com/pl/hengda/app/controller/ConsultantControl.java",
    "groupTitle": "ConsultantControl",
    "sampleRequest": [
      {
        "url": "http://192.168.1.40:8088/hengda/getChannel"
      }
    ]
  },
  {
    "type": "GET",
    "url": "/hengda/getConsltants",
    "title": "获取置业顾问",
    "name": "getConsltants",
    "group": "ConsultantControl",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "ReturnMsg",
            "description": "<p>0：发送成功 1：发送失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回示例",
          "content": "{\n    \"code\":\"0\",\n    \"content\":\n    [\n    {\n         {\n              \"id\": 1,\n               \"name\": \"王小丽\",\n                \"sex\": \"女\",\n              \"age\": 28,\n              \"image\": \"http://pulanbd.iok.la:8800/resource/a.jpg\",\n              \"title\": \"高级销售顾问\",\n              \"phone\": \"123456\",\n              \" desc\": \"8年当地产销售，经验丰富，始终如一的工作态度。\"\n          }\n    }\n    ],\n    \"errorMsg\":null\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/ideagit/apidoc/src/main/java/com/pl/hengda/app/controller/ConsultantControl.java",
    "groupTitle": "ConsultantControl",
    "sampleRequest": [
      {
        "url": "http://192.168.1.40:8088/hengda/getConsltants"
      }
    ]
  },
  {
    "type": "GET",
    "url": "/hengda/getOtherLayout",
    "title": "推荐其他房型",
    "name": "getOtherLayout",
    "group": "ConsultantControl",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "ReturnMsg",
            "description": "<p>0：成功 1：失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回示例",
          "content": "{\n    \"code\":\"0\",\n    \"content\":\n\n[\n         {\n   \"id\": \"1\",\n   \"housename\": \"F户型\",\n   \"housedirect\": \"南北朝向\",\n   \"apartmentlayout\": \"3室2厅2卫\",\n   \"measure\": \"建面118㎡\",\n   \"referenceprice\": \"均价 600万/套\",\n   \"floor\": \"28层\",\n  \"roomnumber\": \"2809\",\n   \"desc\": \"餐厅与客厅相连，室内视野更开阔;格局方正，方便家具摆放;主卧远离活动区，保证休息时不被打扰。\",\n   \"housemappath\": \"http://pulanbd.iok.la:8800/resource/housing01.png\",\n   \"discount\": \"享开盘8.8折 再送价值4万的家私\"\n          }\n],\n\n    \"errorMsg\":null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userid",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求参数示例",
          "content": "userid = 2131",
          "type": "String"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/ideagit/apidoc/src/main/java/com/pl/hengda/app/controller/ConsultantControl.java",
    "groupTitle": "ConsultantControl",
    "sampleRequest": [
      {
        "url": "http://192.168.1.40:8088/hengda/getOtherLayout"
      }
    ]
  },
  {
    "type": "GET",
    "url": "/hengda/getrecLayout",
    "title": "推荐房型",
    "name": "getrecLayout",
    "group": "ConsultantControl",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "ReturnMsg",
            "description": "<p>0：发送成功 1：发送失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回示例",
          "content": "{\n    \"code\":\"0\",\n    \"content\":\n\n\n         {\n   \"id\": \"1\",\n   \"housename\": \"F户型\",\n   \"housedirect\": \"南北朝向\",\n   \"apartmentlayout\": \"3室2厅2卫\",\n   \"measure\": \"建面118㎡\",\n   \"referenceprice\": \"均价 600万/套\",\n   \"floor\": \"28层\",\n  \"roomnumber\": \"2809\",\n   \"desc\": \"餐厅与客厅相连，室内视野更开阔;格局方正，方便家具摆放;主卧远离活动区，保证休息时不被打扰。\",\n   \"housemappath\": \"http://pulanbd.iok.la:8800/resource/housing01.png\",\n   \"discount\": \"享开盘8.8折 再送价值4万的家私\"\n          },\n\n\n    \"errorMsg\":null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userid",
            "description": "<p>用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求参数示例",
          "content": "userid = 2131",
          "type": "String"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/ideagit/apidoc/src/main/java/com/pl/hengda/app/controller/ConsultantControl.java",
    "groupTitle": "ConsultantControl",
    "sampleRequest": [
      {
        "url": "http://192.168.1.40:8088/hengda/getrecLayout"
      }
    ]
  },
  {
    "description": "<p>第一次请求会创建多轮对话返回第一个介绍内容,后面的请求当tag =0 人机问答, tag =1 继续下一个模块讲解。</p>",
    "type": "POST",
    "url": "/hengda/hddialog",
    "title": "多轮对话+问答",
    "group": "HengDaDialog",
    "name": "hddialog",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "ReturnMsg",
            "description": "<p>0：发送成功 1：发送失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回示例 tag =\"\" 第一次访问接口",
          "content": "{\n    \"code\":\"0\",\n    \"content\":\n         \"{\"rc\":0,\"resp\":\"shapan\",\"error\":\"\",\"type\":\"hengda_ai_shapan\",\"tips\":\"沙盘介绍\"}\",\n    \"errorMsg\":null\n}",
          "type": "json"
        },
        {
          "title": "成功返回示例 tag =\"0\" 第二次访问接口",
          "content": "{\n     \"code\": \"0\",\n     \"content\": \"{\"rc\":0,\"resp\":\"公司占地面积20万平方米，建筑面积15万平方米\",\"error\":\"\",\"type\":\"text\"}\",\n     \"errorMsg\": null\n }",
          "type": "json"
        },
        {
          "title": "成功返回示例 tag =\"1\" 第三次访问接口",
          "content": "{\n     \"code\": \"0\",\n     \"content\": \"{\"rc\":0,\"resp\":\"traffic\",\"error\":\"\",\"type\":\"hengda_ai_shapan\",\"tips\":\"交通介绍\"}\",\n     \"errorMsg\": null\n }",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n    \"userid\":\"789456\",\n    \"content\":\"小区的绿化面积\",\n    \"tag\":\"0\"\n}",
          "type": "String"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/ideagit/apidoc/src/main/java/com/pl/hengda/app/controller/HengDaDialog.java",
    "groupTitle": "HengDaDialog",
    "sampleRequest": [
      {
        "url": "http://192.168.1.40:8088/hengda/hddialog"
      }
    ]
  },
  {
    "type": "POST",
    "url": "/hengda/sendMessage",
    "title": "短信发送接口",
    "group": "SmsController",
    "name": "____",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userid",
            "description": "<p>来访者id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>来访者姓名</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n     \"name\":\"张三\",\n    “userid”:\"13215\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "ReturnMsg",
            "description": "<p>0：发送成功 1：发送失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回示例",
          "content": "{\n    \"code\":\"0\",\n    \"content\":\"发送成功\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/ideagit/apidoc/src/main/java/com/pl/hengda/app/controller/SmsControl.java",
    "groupTitle": "SmsController",
    "sampleRequest": [
      {
        "url": "http://192.168.1.40:8088/hengda/sendMessage"
      }
    ]
  },
  {
    "type": "GET",
    "url": "/hengda/chooseConsultant",
    "title": "选择置业顾问",
    "name": "chooseConsultant",
    "group": "facecontroller",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>0：成功 1：失败</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>选择成功</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "errorMsg",
            "description": "<p>code=1时的失败说明</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回示例",
          "content": "{\n    \"code\":\"0\",\n    \"content\":\"选择成功\",\n    \"errorMsg\":null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "userid",
            "description": "<p>来访者id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "conid",
            "description": "<p>置业顾问id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "参数实例",
          "content": "userid = 1321 & conid = 32135",
          "type": "String"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/ideagit/apidoc/src/main/java/com/pl/hengda/app/controller/FaceController.java",
    "groupTitle": "facecontroller",
    "sampleRequest": [
      {
        "url": "http://192.168.1.40:8088/hengda/chooseConsultant"
      }
    ]
  },
  {
    "description": "<p>用户注册接口  image为人脸图像的base64去除头部</p>",
    "type": "POST",
    "url": "/hengda/userRegister",
    "title": "用户注册",
    "group": "facecontroller",
    "name": "userRegister",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>0请求成功 1：请求失败</p>"
          }
        ],
        "content": [
          {
            "group": "content",
            "type": "String",
            "optional": false,
            "field": "isOldCus",
            "description": "<p>0:老客户 1:新客户</p>"
          },
          {
            "group": "content",
            "type": "String",
            "optional": false,
            "field": "userid",
            "description": "<p>客户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回示例",
          "content": "{\n    \"code\":\"0\",\n    \"content\":\n    {\n        \"isOldCus\";\"0\",\n        \"userid\":\"bbf3f55432ac43e589cde6b1f0022714\"\n    }\n\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "image",
            "description": "<p>图像base64 去除头部</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>客户姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>客户手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "channel",
            "description": "<p>渠道</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n  \"image\":\"\",\n  \"username\":\"罗\",\n  \"phone\":\"18676762954\",\n  \"channel\":\"渠道A\",\n}",
          "type": "String"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "E:/ideagit/apidoc/src/main/java/com/pl/hengda/app/controller/FaceController.java",
    "groupTitle": "facecontroller",
    "sampleRequest": [
      {
        "url": "http://192.168.1.40:8088/hengda/userRegister"
      }
    ]
  }
] });

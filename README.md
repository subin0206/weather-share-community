# weather-share-community
weather-share-community
## 기획
기온이 같아도 바람, 햇빛, 습도 등에 따라 체감 온도가 달라지기 때문에 어떤 옷을 입어야 할지 고민이 될 때가 많다. 매해 사계절을 겪지만 유독 내가 얇게 입고 나간 날은 춥고, 두껍게 입고 나간 날은 더워 오늘 날씨가 어떤지 주위에 물어보기도 한다. 고민할 시간을 줄이기 위해, 입은 옷을 날씨와 함께 기록하고 다른 사람들은 어떻게 입었는지 알 수 있는 프로젝트를 기획했다.   

## 개발환경
+ Java 11
+ Spring Boot 2.17
+ MySQL & JPA
+ AWS & RDS & S3

## 와이어프레임
<img src="https://user-images.githubusercontent.com/46561481/159428607-b37daaf6-c45c-4920-8944-f5359a1a3e18.png" width="500px" height="350px"></img>

## ERD
<img src="https://user-images.githubusercontent.com/46561481/159428668-71f49c88-b20e-44d4-bd6b-790c71a6072d.png" width="700px" height="500px"></img>

## API
### User
|||
|------|---|
|method|POST|
|url|/user/join|
|file|1개, name= "profile", null 가능|
|Body|{"userName" : "bbbb", "userEmail":"bbbb@bbbb.com", "pwd" : "bbbb"}|   

```
{
    "statusCode": 200,
    "responseMessage": "회원가입 성공",
    "data": null
}
```   
|||
|------|---|
|method|POST|
|url|/user/login|
|Header|X-AUTH-TOKEN|
|Body|{"userName" : "bbbb","pwd" : "bbbb"}|   

```
{
    "statusCode": 200,
    "responseMessage": "로그인 성공",
    "data": "token값"
}
```   



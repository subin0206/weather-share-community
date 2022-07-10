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

## base_url
http://3.38.56.88:8080/

## API
### User
#### 회원가입
|||
|------|---|
|method|Post|
|url|/user/join|
|file|1개, name= "profile", null 가능|
|Body|{"nickName" : "bbbb", "userEmail":"bbbb@bbbb.com", "pwd" : "bbbb"}|   
|참고| 프로필 제외한 나머지 속성들 null 불가능, 이메일 @ 없이 보내면 오류

```
{
    "statusCode": 200,
    "responseMessage": "회원가입 성공",
    "data": null
}
```   
#### 로그인
|||
|------|---|
|method|Post|
|url|/user/login|
|Header|X-AUTH-TOKEN|
|Body|{"userEmail" : "bbbb","pwd" : "bbbb"}|   

```
{
    "statusCode": 200,
    "responseMessage": "로그인 성공",
    "data": "token값"
}
```   
#### 닉네임 중복체크
|||
|------|---|
|method|Post|
|url|/check/nick_name|
|Body|{"nickName" : "bbbb"}|   
|참고| 중복된 닉네임이면 true, 없는 닉네임이면 false 

```
{
    true or false
}
```   
#### 이메일 중복체크
|||
|------|---|
|method|Post|
|url|/check/user_email|
|Body|{"userEmail" : "bbbb@bbbb.com"}|   
|참고| 중복된 이메일이면 true, 없는 이메일이면 false 

```
{
    true or false
}
```   
#### 마이페이지
|||
|------|---|
|method|Get|
|url|/user/mypage|
|Header|X-AUTH-TOKEN|   

```
{
    "statusCode": 200,
    "responseMessage": "마이페이지",
    "data": {
        "id": 10,
        "userName": "eeee",
        "userEmail": "eeee@eeee.com",
        "profileUrl": "이미지 url"
    }
}
```  
#### 마이페이지 수정
|||
|------|---|
|method|Post|
|url|/user/mypage/edit/profile|
|Header|X-AUTH-TOKEN|   

```
{
    "statusCode": 200,
    "responseMessage": "마이페이지 수정 성공",
    "data": null
}
```  
#### 내가 좋아요 한 글들
|||
|------|---|
|method|Get|
|url|/user/likes|
|Header|X-AUTH-TOKEN|    
```
{
    "statusCode": 200,
    "responseMessage": "좋아요 리스트",
    "data": [
        {
            "id": 9,
            "img": "가장 맨 처음 이미지 url",
            "memberName": "a",
            "boardId": 36
        },
        {
            "id": 10,
            "img": "가장 맨 처음 이미지 url",
            "memberName": "eeee",
            "boardId": 34
        }
    ]
}
```
#### 스크랩 목록
|||
|------|---|
|method|Get|
|url|/user/scrape/list|
|Header|X-AUTH-TOKEN|    
```
{
    "statusCode": 200,
    "responseMessage": "스크랩 리스트",
    "data": [
        {
            "id": 2,
            "img": "가장 맨 처음 이미지 url",
            "memberName": "eeee",
            "boardId": 25
        },
        {
            "id": 10,
            "img": "가장 맨 처음 이미지 url",
            "memberName": "eeee",
            "boardId": 26
        }
    ]
}
```   
### Board   
#### 글쓰기 
|||
|------|---|
|method|Post|
|url|/board/save|
|Header|X-AUTH-TOKEN|  
|Body|{"requestDto":{ "content": "하늘상태 테스트", "privacy": true, "status" : "HOT", "skyCode":"흐림","presentTemperature":"30","highestTemperature":"32","lowestTemperature":"24"},"clothes" : {"top" : "후드", "bottom":"트레이닝 바지", "outerClothing":"자켓","shoes" : "운동화", "accessory1":"모자", "accessory2":"선글라스"}|
|image|null 불가능, 3개까지|
|참고|status는 HOT, WARM, BEST, COLD, COOL 중 하나로 보내야함|

```
{
    "statusCode": 200,
    "responseMessage": "글 올리기 성공",
    "data": null
}
```  
#### 글 상세보기
|||
|------|---|
|method|Get|
|url|/board/{boardId}|

```
{
    "statusCode": 200,
    "responseMessage": "글 성공",
    "data": {
        "id": 137,
        "content": "테스트",
        "createDate": "2022-07-10T13:20:41.567759",
        "skyCode": "흐림",
        "presentTemperature": "30",
        "highestTemperature": "32",
        "lowestTemperature": "24",
        "codyDate": "2022-07-10",
        "clothes": null,
        "images": [],
        "memberResponseDto": {
            "id": 2,
            "nickName": "kim3",
            "userEmail": "cccc@cccc.com",
            "profileUrl": ""
        },
        "clothesResponseDto": {
            "top": "후드",
            "bottom": "트레이닝 바지",
            "outerClothing": "자켓",
            "shoes": "운동화",
            "accessory1": "",
            "accessory2": ""
        }
    }
}
```   
#### 글 전체조회(최신순)
|||
|------|---|
|method|Get|
|url|/boards|

```
{
    "statusCode": 200,
    "responseMessage": "글 성공",
    "data": [
        {
            "id": "58",
            "content": "test3",
            "privacy": false,
            "createDate": "2022-05-01T13:49:36.6563063",
            "status": "HOT",
            "memberName": "a",
            "profileUrl": "profile url",
            "imgUrl": "글의 첫번째 이미지 url"
        },
        {
            "id": "61",
            "content": "test4",
            "privacy": false,
            "createDate": "2022-05-01T13:49:36.6602927",
            "status": "HOT",
            "memberName": "eeee",
            "profileUrl": "profile url",
            "imgUrl": "글의 첫번째 이미지 url"
        }
    ]
}
```   
#### 글 삭제
|||
|------|---|
|method|Delete|
|url|/board/{boardId}/delete|
|Header|X-AUTH-TOKEN|
```
{
    "statusCode": 200,
    "responseMessage": "성공"
    "data": null
}
```    

#### 좋아요
|||
|------|---|
|method|Post|
|url|/board/{boardId}/likes|
|Header|X-AUTH-TOKEN|
|참고|이미 있는 boardId인 경우 좋아요 취소로 동작|   
```
{
    "statusCode": 200,
    "responseMessage": "좋아요 성공" or "좋아요 취소"
    "data": null
}
```   
#### 스크랩
|||
|------|---|
|method|Post|
|url|/board/{boardId}/scrape|
|Header|X-AUTH-TOKEN|
|참고|이미 있는 boardId인 경우 스크랩 취소로 동작|   
```
{
    "statusCode": 200,
    "responseMessage": "스크랩 성공" or "스크랩 취소"
    "data": null
}
```   
#### 댓글
|||
|------|---|
|method|Post|
|url|/board/{boardId}/reply|
|Header|X-AUTH-TOKEN|
|Body|{"content" : "hi"}|   
```
{
    "statusCode": 200,
    "responseMessage": "댓글달기"
    "data": null
}
```   
#### 답글
|||
|------|---|
|method|Post|
|url|/board/{boardId}/reply/{replyId}|
|Header|X-AUTH-TOKEN|
|Body|{"content" : "hi"}|   
```
{
    "statusCode": 200,
    "responseMessage": "답글달기"
    "data": null
}
``` 
#### 댓글목록
|||
|------|---|
|method|Get|
|url|/board/{boardId}/replies|
```
{
    "statusCode": 200,
    "responseMessage": "댓글 목록",
    "data": [
        {
            "id": 1,
            "content": "hi",
            "memberName": "eeee",
            "profileUrl": "profile img url"
        },
        {
            "id": 2,
            "content": "hi2",
            "memberName": "eeee",
            "profileUrl": "profile img url"
        },
        {
            "id": 3,
            "content": "a_reply",
            "memberName": "a",
            "profileUrl": "profile img url"
        }
    ]
}
``` 
#### 답글목록
|||
|------|---|
|method|Get|
|url|/reply/{replyId}|
```
{
    "statusCode": 200,
    "responseMessage": "답글 목록",
    "data": [
        {
            "id": 1,
            "content": "reply test",
            "memberName": "kim3",
            "profileUrl": ""
        },
        {
            "id": 2,
            "content": "reply test2",
            "memberName": "kim3",
            "profileUrl": ""
        }
    ]
}
``` 
#### 댓글 삭제
|||
|------|---|
|method|Post|
|url|/board/reply/{replyId}|
|Header|X-AUTH-TOKEN|
```
{
    "statusCode": 200,
    "responseMessage": "댓글삭제"
    "data": null
}
```

### 신고 & 차단  
#### 게시물 신고
|||
|------|---|
|method|Post|
|url|/report/board|
|Header|X-AUTH-TOKEN|
|참고|10번 이상 신고되면 글 삭제|
```
{
    "statusCode": 200,
    "responseMessage": "게시물 신고"
    "data": null
}
```
#### 댓글 신고
|||
|------|---|
|method|Post|
|url|/comment/board|
|Header|X-AUTH-TOKEN|
|참고|10번 이상 신고되면 댓글 삭제|
```
{
    "statusCode": 200,
    "responseMessage": "댓글 신고"
    "data": null
}
```   
#### 차단
|||
|------|---|
|method|Post|
|url|/block/create|
|Header|X-AUTH-TOKEN|
```
{
    "statusCode": 200,
    "responseMessage": "차단"
    "data": null
}
```   

### follow   
#### 팔로우 하기   
|||
|------|---|
|method|Post|
|url|/follow/{toMemberId}|
|Header|X-AUTH-TOKEN|
```
{
    "statusCode": 200,
    "responseMessage": "팔로우 성공"
    "data": null
}
```    
#### 팔로우 취소 하기
|||
|------|---|
|method|Delete|
|url|/follow/{toMemberId}|
|Header|X-AUTH-TOKEN|
```
{
    "statusCode": 200,
    "responseMessage": "언팔로우 성공"
    "data": null
}
```   
#### 해당 유저를 팔로우하는 사람들 리스트
|||
|------|---|
|method|Get|
|url|/follow/getFollower/{toMemberId}|
|참고|로그인 여부 상관 없이 어떤 유저가 팔로우하는 리스트를 확인 할 수 있음, toMemberId는 팔로우 리스트를 확인하고 싶은 유저, 로그인 없이 확인 가능|
```
{
    "statusCode": 200,
    "responseMessage": "팔로우 리스트",
    "data": [
        {
            "fromMemberId": 3,
            "toMemberId": 4
        },
        {
            "fromMemberId": 3,
            "toMemberId": 5
        }
    ]
}
``` 

#### 내가 팔로우 한 사람들 
|||
|------|---|
|method|Delete|
|url|/follow/getFollowing|
|Header|X-AUTH-TOKEN|
```
{
    "statusCode": 200,
    "responseMessage": "팔로잉 리스트",
    "data": [

        {
            "fromMemberId": 5,
            "toMemberId": 3
        }
    ]
}
```   

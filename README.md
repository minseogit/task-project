# 블로그 검색 서비스 개발
### 1. 블로그 검색
- 새로운 검색 소스가 추가될 수 있음을 고려해 ApiType으로 호출할 API를 구분했습니다.
    - ApiType이 존재하지 않으면 INVALID_SEARCH_API_TYPE 에러를 반환합니다.
    - Kakao API 호출 실패시 Naver API를 호출합니다.
    - Naver API 호출 실패시 API_CALL_ERROR 에러를 반환합니다.
- 검색 내역을 저장하기 위해 기존 검색 이력(SEARCH_HISTORY)을 조회합니다.
    - 기존 검색 내역이 존재하지 않으면 검색 내역을 새로 생성합니다.
    - 검색 내역 조회시 동시성 이슈를 고려해 비관적 락을 걸어주었습니다.
    - 검색 내역이 존재하면 검색 횟수를 증가합니다.

### 2. 인기 검색어 목록
- 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드와 검색 횟수를 조회합니다.
- 검색어의 검색 내역이 존재하지 않으면 SEARCH_HISTORY_NOT_FOUND 에러를 반환합니다.
<br>

## 개발 환경

|구분|Skill|
|:--|:--|
|언어 | Java 11|
|프레임워크 | SpringBoot 2.7.9|
|DBMS | H2|
|빌드 툴| Gradle|
|Persistence |JPA|

<br>

## Database
#### SEARCH_HISTORY

|Column|Type|
|:--|:--|
|history_id | Long|
|keyword | String|
|count | int|

검색 내역을 저장하는 테이블로 ID, 검색키워드, 검색횟수로 구성되어 있습니다.

<br>

## API 명세

### 1. 블로그 검색 API
#### Request
|구분|값|
|:--|:--|
|URL|/search/blogs|
|HTTP Method|Get|
|Parameter|SearchBlogRequest, ApiType|

`SearchBlogRequest`

|Name|Type|Description|Required|
|:--|:--|:--|:--|
|query|String|검색을 원하는 질의어|O|
|sort|SearchSortType|결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순)|X|
|page|int|결과 페이지 번호|X|
|size|int|한 페이지에 보여질 문서 수|X|

`ApiType` : Enum 타입으로 Kakao, Naver로 구성

#### Response
|Http 상태 코드|Return 값|
|:--|:--|
|OK(200)|SearchBlogResponse|

`SearchBlogResponse`
- Meta

|Name|Type|Description|
|:--|:--|:--|
|total_count|int|검색된 문서 수|
|pageable_count|int|total_count 중 노출 가능 문서 수|
|is_end|Boolean|현재 페이지가 마지막 페이지인지 여부|

- documents

|Name|Type|Description|
|:--|:--|:--|
|title|String|문서 제목|
|contents|String|문서 본문 중 일부|
|url|String|문서 URL|
|datetime|Datetime|문서 글 작성시간|

<br>

### 2. 인기 검색어 목록 조회 API
#### Request
|구분|값|
|:--|:--|
|URL|/search/rank|
|HTTP Method|Get|

#### Response
|Http 상태 코드|Return 값|
|:--|:--|
|OK(200)|List&lt;SearchHistory>|

<br>

## 에러코드 및 메시지
|Code|Message|
|:--|:--|
|SEARCH_HISTORY_NOT_FOUND|검색 결과가 존재하지 않습니다.|
|INVALID_SEARCH_API_TYPE|API TYPE이 유효하지 않습니다.|
|API_CALL_ERROR|API 호출에 실패했습니다.|
|INVALID_PARAMETER|유효하지 않은 파라미터가 포함되어 있습니다.|
|INTERNAL_SERVER_ERROR|내부 서버에 오류가 발생했습니다.|

<br>

## 라이브러리
  
|라이브러리|사용 이유|
|:--|:--|
|Spring Cloud OpenFeign|인터페이스 기반으로 HTTP 요청을 간단한 코드로 구현 가능|
|Spring Cloud Resilience4j|API 호출시 클라이언트에게 에러가 아닌 다른 응답을 제공하기 위한 Circuit Breaker로 사용.<br> 카카오 블로그 검색 API에 장애가 발생한 경우, 네이버 블로그 검색 API를 호출하기 위해 사용했습니다.|
|Swagger|API 문서화를 자동화하고 테스트|
|Lombok|자주 사용하는 코드 자동 생성|

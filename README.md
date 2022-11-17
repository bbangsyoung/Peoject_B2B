# B2B Market.biz 🏕 캠핑용품 B2B 웹사이트 <img src="https://img.shields.io/badge/SpringBoot-2e6d00?style=flat-square&logo=Java&logoColor=white">


`SpringBoot`와 `Mysql` 이용한 `B2B(Business-to-Business)` 웹사이트<br>
개발기간 : 2022.11.01 ~ 진행중. 

✅ 사이트 바로가기:<b> http://43.200.119.189:8080 </b> <br>
AWS 프리티어 계정으로, 최초 접속 시 다소 느린 점 양해부탁드립니다. <br>

---


<img src="http://hohk.dothome.co.kr/load/data/kt180401/166870159530459680.jpg">

## 🖥 프로젝트 소개
- 프로젝트명 : B2B Market.biz 캠핑용품 B2B 웹사이트
- 개발기간 <img src="https://img.shields.io/badge/22/11/01 ~ 22/11/16-ffce66?style=flat&logo=java&logoColor=white">
- 타겟층 : 벤더사 관리를 하는 기업(공급사, 제조사 등)
- 기획배경 : 기존 카페24 등에서 제공하는 템플릿으로는 일반판매 쇼핑몰 밖에 구현하지 못하는 한계점이 있음. <br>
그래서 제조사들은 B2B사이트를 만들때면 매번 개발자에게 개인 의뢰를 해야함.
해당목적으로 이미 만들어진 템플릿이 있으면 어떨까? 라는 발상을 시작으로 실제 서비스 할 수 있을법한 웹사이트를 만드는 것이 목표.
- 기획목적 : 1차 완성 후 계속 보강하여 언젠가 B2B 공급업체전용 템플릿으로 판매하고싶다는 불순한 의도(카페24, 메이커스 등)
- 개발단계 별 구분
   - 1차 : <u>기본 쇼핑몰 기능(상품등록,수정,삭제 / 주문관리 등)</u> - 완료
   - 2차 : 회원 별 차등혜택(가격 및 배송비 정책 등), 실 구매가 기준 거래명세서 조회 및 발행가능
<br>

## 💡 개발환경 및 기술 세부스택 
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=black"> <img src="https://img.shields.io/badge/Spring Security-2b6d00?style=for-the-badge&logo=Spring Security&logoColor=white">  <img src="https://camo.githubusercontent.com/a0f9c9f1295e65f8c081e5e6073840e309726163c310542f8c0acb5aa60ba5ad/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a4156412d3030373339363f7374796c653d666f722d7468652d6261646765266c6f676f3d6a617661266c6f676f436f6c6f723d7768697465"> <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"><br>
 <img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=for-the-badge&logo=Amazon AWS&logoColor=white">  <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=Amazon RDS&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=black">


- Spring Boot
- Spring Security
- Java 11
- IntelliJ IDEA
- Mysql
- LINUX(Ubuntu), AWS Cloud
- HTML5, CSS3, Javascript ES6

<br>

## 🚀 요구사항
<img src="http://hohk.dothome.co.kr/load/data/kt180401/166870314896660096.jpg">

<details>
<summary>그 외 더보기</summary>
<div markdown="1">
<img src="http://hohk.dothome.co.kr/load/data/kt180401/166870315447182400.jpg">
</div>
</details>



<br>

## 📌 코드 설명
```
추가예정
```


<br>

## 😫 현재 이슈파악 및 관리

- 현재 장바구니에 담았다가 선택 후 결제할 때 넘어가지 않는 오류 있음. (타임리프~자바스크립트 관련 문제로 예상됨)
- 기능을 조금 더 추가하고 싶지만 공부를 겸임해서 따로 게시판 구현 후 이식할 예정

<b>이슈관리 및 계속 업데이트 중.</b>
```
UPDATE List
2022.11.10  1차 기능(ver.0.1), 로컬환경 테스트 완
2022.11.12  server연동에 앞서 경로 및 의존성 리패키징
2022.11.12  AWS jar 배포 테스트 완
2022.11.13  RDS로 DB연동 및 테스트 완
2022.11.13  swap 메모리 할당
2022.11.14  배포 후 cart 페이지 이동 시 파일을 못찾는 문제발생 -> 해결(Thymeleaf오류)
2022.11.15  다른PC에서 메인프론트 오류 확인. 수정예정. -> 미세조정
```
순차적 추가 예정 - 카테고리 별 분류, 메인 이미지 추가 및 교체, 안내 팝업<br>

# B2B Market.biz : 캠핑용품 B2B 웹사이트 <img src="https://img.shields.io/badge/SpringBoot-2e6d00?style=flat-square&logo=Java&logoColor=white">


`SpringBoot`를 이용한 `B2B(Business-to-Business)` 웹사이트<br>
개발기간 : 2022.11.01 ~ 진행중. 아래로 추가.

http://43.200.119.189:8080/ <br>
AWS 프리티어 계정이다보니 페이지마다 처음 켜질 때 조금 느립니다.

---
1차 기능 - 기본적인 상품등록, 수정, 장바구니담기, 구매, 구매내역, 회원가입 및 로그인(스프링 시큐리티)

```
UPDATE List
2022.11.10  1차 기능(ver.0.1), 로컬환경 테스트 완
2022.11.12  server연동에 앞서 경로 및 의존성 리패키징
2022.11.12  AWS jar 배포 테스트 완
2022.11.13  AWS&RDS연동 및 DB테스트 완
2022.11.13  swap 메모리 할당
```


이슈관리 및 계속 업데이트 중. <br>

2022.11.13 기준 현재 오류 - 로컬에서는 멀쩡했는데 서버올리고나니 cart 페이지 이동 시 파일을 못찾는 문제발생. <br>
이후 추가 예정 - 카테고리 별 분류, 메인 이미지 추가 및 교체, 안내 팝업

# Java
Java를 이용한 프로그래밍

- Programming
- Java
- [Java](https://github.com/hyejeong99/Java) : Java Programing

### 1. JAVA_1
#### scanner_box
스캐너를 이용해 직사각형 만들기

#### hello_java
main()함수에서 사용할 문자 1개와 몇개 인쇄할 지를 입력 받아서 Hello Java를 출력하기

[입력]
라인문자는? : *
문자 길이는? : 30

[출력]
******************************
Hello, Java
******************************

#### FindNum
FindNum 프로그램

#### box_class
직사각형의 둘레와 면적을 구하는 프로그램을 작성한다. 직사각형의 가로와 세로를 각각 W와 H라고 하면 직사각형의 면적은 "WxH"가 되고 둘레는 "2x(W+H)"가 된다. 

#### bank_account
![noname01](https://user-images.githubusercontent.com/59854960/113369288-f97f1000-939b-11eb-96ca-bfde2c5e4488.png)

배열에 대하여 실습하여 보자. 회사에서 직원들을 관리하는 프로그램을 작성한다. 직원들의 이름, 주소, 연봉, 전화번호를 가지고 있다. 직원은 Employee클래스로 표현된다. 회사 전체의 직원은 Employee의 배열 안에 저장된다.

#### Vehicle
![noname02](https://user-images.githubusercontent.com/59854960/113369378-30edbc80-939c-11eb-8a20-738b692d4a21.png)

이동 수단의 속도와 연비를 출력한다.

#### shape
사각형, 삼각형, 원의 각 도형의 넓이를 계산하라.

#### fileCopy
파일을 복사한다.
이후 복사가 잘 되었는지 확인하는 프로그램이다.

#### copyByte_Buffer
- CopyByte myPicture.jpg copyMyPicture.jpg
: 바이트 단위로 카피해 주는 프로그램
- CopyBuffer myPicture.jpg copyMyPicture.jpg
: 버퍼화된 1024 단위로 읽어서 써주는 프로그램

각각을 명령창에서 실행하고, 실행 시간을 비교한다.

#### finalProject
UI를 이용한 편의점 프로그램이다.
- Management 객체 : 물품 리스트에 물품을 등록하고, 삭제한다.

이를 위해 물품 그룹을 찾는 함수와 물품 명으로 물품명을 포함하고 있는 물품객체의 위치를 반환하는 함수를 구현했다.

- 사용자 : 구매하기를 원하는 물품과 수량를 입력한다.

사용자에게 구매총액을 알려주고, 만약 사용자가 이 물품을 원하는 수량만큼 구매하기를 원한다면 구매절차(즉, 재고를 줄이고, 매출을 증가시킴)를 진행한다. 만약 재고가 없을시에는 익셉션을 받아서 재고가 없음을 사용자에게 알려준다.
사용자에게 "분류 -> 물품(번호로)"를 입력받고, 구매 수량도 입력 받아야 한다.
구매자가 원하는 물품의 값을 우선 알려줘야 한다. 그리고, 구매자가 돈이 있고, 사겠다고 했을 때 구매 절차가 이뤄진다.

### 1. JAVA_2
#### project_to_List
JAVA_1의 finalProject를 분석해서 Linked List 자료구조 객체로 바꾸어 구현한다.
Management 클래스 내의 array 배열을 Linked List 로 바꾸고, Management 내의 멤버 함수 구현 부분을  Linked List 멤버함수를 이용해서 간단한 형태로 바꿔준다.

#### project_to_DataStructure
프로젝트를 보완된 완성본 자료구조 객체로 바꾸기 버전으로 만든다.

#### project_to_Object
프로젝트를 Object로 Goods 객체를 저장한다.

#### project_modify_UI
지금까지 Management class가 수정된 것을 바탕으로 UI 클래스를 수정한다.

#### project_to_GUI
프로젝트 진행한 내용을 GUI 이벤트를 구현한다.

#### GUI_chat
GUI 버전1 대 1 채팅 프로그램을 만든다.

- 클라이언트 :  IP 주소 및 Port  넘버 넣고 연결해야 한다.
- 서버 : 지금 연결된 클라이언트가 누구인가를 항상  ip를 볼 수 있게  GUI 설계 한다.

#### figure_Grahpics
다양한 도형 그리기

#### project_to_JDBC
JDBC 사용해서 GUI 프로그램 바꿔준다. 
GUI에서 직접 DB statement를 사용해서 DB를 access 하는 방법을 사용한다.

#### JDBC_Applet
![자바애플릿](https://user-images.githubusercontent.com/59854960/113370620-457f8400-939f-11eb-9bc3-152cf05480b8.png)
jdbc GUI 프로그램을 jdbc Applet 프로그램으로 바꾼다.

##### 편의점 관리 프로그램
![처음](https://user-images.githubusercontent.com/59854960/113370855-e3734e80-939f-11eb-9515-2b1ab8837bf5.png)

처음 시작하면 구매자, 관리자, 종료 버튼을 선택할 수 있다.

(1)구매자 모드
![구매자](https://user-images.githubusercontent.com/59854960/113370848-e1a98b00-939f-11eb-8b99-8881d61833f7.png)

구매자 모드에 진입을 성공했다.
라벨&배경 색을 바꿔준다.

(1-1)카테고리 선택
![카테고리](https://user-images.githubusercontent.com/59854960/113370857-e40be500-939f-11eb-9583-6bbd7bcef05c.png)

데이터베이스에 있는 물푼 목록을 테이블에 보여준다.

![카테고리2](https://user-images.githubusercontent.com/59854960/113370858-e40be500-939f-11eb-97b7-2acf7bbaa66b.png)

카테고리 선택 시 카테고리에 맞는 물품을 띄워준다.
"우유"카테고리를 선택해 그에 맞는 물품을 보여주고 있다.

(1-2)
![구매](https://user-images.githubusercontent.com/59854960/113370847-e110f480-939f-11eb-9729-3b13e0901b92.png)
![구매후](https://user-images.githubusercontent.com/59854960/113370850-e2422180-939f-11eb-9a56-43fed3a0379f.png)

라면 카테고리에서 짜파게티를 선택 후, 구매 수량 5개를 입력하고 구매 버튼을 누르니 구매 금액을 알려준다. 
구매를 할 것이면 확인버튼을 눌러 구매를 진행하면 된다.
구매를 진행한 후 수량이 줄어든 것을 확인할 수 있다.

(2)관리자 모드
![관리자](https://user-images.githubusercontent.com/59854960/113370845-e0785e00-939f-11eb-9319-faa8395a4beb.png)

관리자 모드로 진입하자, 라벨&배경 색이 바뀐 것을 확인할 수 있다.

(2-1)물품 추가
![물품추가](https://user-images.githubusercontent.com/59854960/113370853-e2dab800-939f-11eb-95f6-c72a2c82f666.png)

추가한 물품을 데이터베이스&테이블에 추가해준다.

(2-2)물품 삭제
![물품삭제](https://user-images.githubusercontent.com/59854960/113370851-e2422180-939f-11eb-9b76-0140e6b263dd.png)

테이블을 누르고 삭제 버튼을 누르면 옵션팬을 띄워줘 삭제할 것인지 물어본다. 확인 버튼을 누르면 물품 삭제를 진행한다.

(3)종료 버튼
![종료](https://user-images.githubusercontent.com/59854960/113370854-e2dab800-939f-11eb-8014-755b087af15c.png)

옵션팬을 띄워 종료할 것인지 묻고, 확인을 누르면 프로그램을 종료한다.

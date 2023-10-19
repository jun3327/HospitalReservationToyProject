# 병원예약 토이프로젝트 - B612 백엔드 과제

## *Requirements
![홍익병원 요구사항](https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/a9b190f5-1df3-4f78-893b-a559f209a252)

- 병원은 여러개. 병원 마다 여러 진료과가 있다. 진료과에는 여러 의사가 소속되어 있다.

- ERD: 
![image](https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/2e655951-f5d2-4a16-b436-ef1f5ea386f6)

## *Refenrence 
- [스프링 부트와 JPA 활용 강의](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1/dashboard) (css과 html 레이아웃은 강의의 예시코드를 참고했습니다)

## *고민한 사항들
 1. 진료과와 병원, 의사의 관계를 어떻게 짤지 고민해 봤음. 병원이 한 개라면 별 고민할 필요도 없지만, 병원이 여러개인 통합 예약 시스템을 고려한다면 조금 복잡해진다. 병원 -> 진료과 -> 의사 순서대로 객체를 생성하는 방식으로 진행했다. 각각의 연관관계는 단계적으로 매핑된다. 

 2. 환자 이름 중복 가입 방지를 위한 검증 로직을 비즈니스 로직에 구현하지 않고, 동시성 문제 방지를 위해 name 필드에 unique 제약 조건을 걸어놨다. 중복 가입을 시도할 때 리포지토리 계층에서 DataIntegrityViolationException가 발생하는데, 서비스 계층에서 try catch로 잡고 컨트롤러에 던져서 화면에 에러 메시지를 띄울려고 했으나.. 잘 안됐다. DB에서 발생하는 예외는 try catch로 잡기 어려운것 같다. 그래서 GlobalExceptionHandler로 처리했다. 사실 제대로 한 것 같지는 않지만 일단 구현에만 집중했다. (3번에서 말하듯 나중에 안 당연한 사실 -> try catch로 잡기 어려운게 아니라catch 자체가 실행이 안되었던 것...) 
![dddd](https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/c1747d29-d77f-4037-b831-9e089ec3d2d1)

 3. 2번에서 구현했던 예외 방식을 조금 수정했다. 2번에서 처럼 하면 다른 unique 제약 조건이 걸린 필드의 모든 DataIntegrityViolationException 예외에 대해 동일하게 처리되기 떄문에, 아래와 같이 각 도메인 별 사용자 정의 예외를 사용했다.
![ssss](https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/952ca0ab-f650-455c-b76b-d4d9a235a57f)

- 처음에는 try catch로 DataIntegrityViolationException를 서비스 계층에서 잡으려고 생 난리를 쳤는데, @Transanctional이 있는 서비스계층 메소드에 try catch를 백만번 적어 봤자 커밋 되는 시점 이전, 즉 메소드가 끝나기 전에는 db에 반영 되지 않으므로 try catch로 db관련 예외를 잡을 수 있을리가 없었다...즉 catch가 실행되지 않고 그 후에 예외가 터졌던 것.. controller에 서비스 계층의 예외를 넘기고 컨트롤러에서 에러메시지를 작성할 수도 있겠지만 관심사 분리 원칙에 따라 지양했다. 어쩔 수 없이 리포지토리에서 강제로 flush() 메소드를 추가해 예외를 처리했다.
-아래 코드는 PatientService 일부
![image](https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/48e88de2-f7f8-4b86-9d63-c5aa019a2397)

 4. hospital 도메인과 department 도메인 사이의 관계 상 어쩔 수 없이 hospital 컨트롤러에서 department 자원을 만질 수 밖에 없다. 그런데 url 경로, 컨트롤러 내부 처리 등등 한 컨트롤러에서 다른 자원을 다루는 건 가독성이 떨어진다고 생각했다. 그래서 어떤 방법이 있나 검색했는데, 스프링 MVC 기능 중 서버 내에서 사용 하는 forward 기능이 있다는 것을 알았다. forward로 hospital 컨트롤러에서 department 컨트롤러로 데이터를 전달하면 department 관련 동작들을 Get 하거나 Post할 때 온전히 department 컨트롤러에서 더 깔끔하게 처리할 수 있다.  
- 원래 코드:
- (hospital 컨트롤러)
![image](https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/58390a8d-1d92-4167-bbed-2e01cd703448)

- forward 사용 코드:
- (hospital 컨트롤러)
![image](https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/ee698e86-de2e-4e93-a4df-45cc57aa2b2c)

- (department 컨트롤러)
![image](https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/66ea3621-4f0e-48ed-aab1-343d7223a404)
- 위와 같이 설정하면, hospital과 department 컨트롤러 사이를 나눠서 보기가 좋다. 이제 department 관련 코드들은 department 컨트롤러에서 작성하면 된다.
(* 수정: 생각해보니 Department 컨트롤러 클래스에 RequestMapping을 departments 시작 부분으로 해주면 굳이 Hospital 컨트롤러에서 forward를 할 필요가 없었다. 따라서 Hospital 컨트롤러의 코드는 지우고, 아래와 같이 Department 컨트롤러 클래스를 만들었다. MVC 강의 안들어서 이런 부분이 힘들다) 
![image](https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/81d62f29-cf77-448f-b4d0-5e27ae3d9654)


## 수정해야할 것들
1. 엔티티로 정보 주고받는 것들 전부 DTO로 바꾸기
2. 예외 처리 클래스가 너무 잡다해 보임.
3. API 방식으로 애플리케이션 수정해보기.
4. 컨트롤러에서 View 보여줄 때, 서로 다른 엔티티 두 개의 정보에 대한 것을 DB에 접근해서 가져오는데 이 때 select 쿼리가 여러 방 나간다. fetch join으로 한번에 긁어와서 처리할 수 있을 듯.
5. OneToONe 양방향 관계에서 각각의 엔티티에 서로 지연로딩 전략을 사용했음에도, 연관관계의 주인이 아닌 쪽의 엔티티를 db에서 조회하면 다른 엔티티도 select하는 쿼리가 나가는 N+1 문제 발생.. Reservation과 DiagnosisInfo 엔티티가 서로 일대일 양방향 관계이고 연관관계의 주인을 DiagnosisInfo로 설정해놨는데, Reservation을 select하는 jpql문을 실행하면 Reservation 테이블을 select하고 후에 DiagnosisInfo 테이블도 select해버린다
![image](https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/0e6b4da6-6e63-4357-ad24-97e0443d6303)
- 이유: 데이터베이스에서 연관관계의 주인이 아닌 쪽의 테이블에서는 주인 테이블의 식별자에 해당하는 column을 갖고 있지 않다. 객체세상에서 양방향이라고 하더라도 DB테이블 입장에서는 한 쪽에만 FK가 있으면 양쪽으로 join 가능해서 상관없기 때문이다. 중요한 점은 지연로딩 전략이 프록시를 사용하는데, 상대방 엔티티가 null 값이 가능한 상황에서 해당 값을 프록시로 감싸는 것은 불가능하다(프록시 기능의 한계). Reservation 입장에서는 DiagnosisInfo 테이블에 대한 Pk나 정보가 없기 때문에 DiangnosisInfo에 대응시킬 프록시를 생성하기 전에 그게 null인지 아닌지부터 확인해야한다. 그래서 DiagnosisInfo를 select하는 쿼리가 추가적으로 나가는 것이다.
- 근데 OneToMany나 ManyToOne 의 One에서는 지연로딩이 잘 동작한다. 이유는 Many에 해당하는 쪽을 컬렉션을 사용해서 초기화 하기 때문에(설령 컬렉션이 비어있어도 빈 컬렉션과 null 상태는 다르다), 연관관계 사이가 non-null이며 프록시 객체를 생성할 수 있기 때문이다.
- 해결: optional = false 조건을 걸어서 null 값이 아님을 제약조건으로 걸어서 시도했는데 왜 안되는지 모르겠고,, 그래서 그냥 fetch join으로 한꺼번에 가져왔다.

## 그냥 해보고 싶은 기능들
1. 의사하고 환자 채팅방 기능
2. 의사 리뷰 기능
3. 의사/환자에게 예약 확정 알림 기능

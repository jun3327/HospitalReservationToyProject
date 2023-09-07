# *HospitalReservationToyProject
B612 백엔드 과제

## *Requirements
<img width="606" alt="홍익병원 요구사항" src="https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/a9b190f5-1df3-4f78-893b-a559f209a252">

## *Refenrence 
#### https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1/dashboard (css과 html 레이아웃은 강의의 예시코드를 참고했습니다)

## *고민한 사항들
##### 1. 진료과와 병원, 의사의 관계를 어떻게 짤지 고민해 봤음. 병원이 한 개라면 별 고민할 필요도 없지만, 병원이 여러개인 통합 예약 시스템을 고려한다면 조금 복잡해진다. 나는 진료과의 전화번호와 소속 병원을 기준으로 구분해서 등록 시 제약 조건을 걸어놨다. 

##### 2. 환자 이름 중복 가입 방지를 위한 검증 로직을 비즈니스 로직에 구현하지 않고, 동시성 문제 방지를 위해 name 필드에 unique 제약 조건을 걸어놨다. 중복 가입을 시도할 때 리포지토리 계층에서 DataIntegrityViolationException가 발생하는데, 서비스 계층에서 try catch로 잡고 컨트롤러에 던져서 화면에 에러 메시지를 띄울려고 했으나.. 잘 안됐다. DB에서 발생하는 예외는 try catch로 잡기 어려운것 같다. 그래서 GlobalExceptionHandler로 처리했다. 사실 제대로 한 것 같지는 않지만 일단 구현에만 집중했다.
<img width="809" alt="dddd" src="https://github.com/jun3327/HospitalReservationToyProject/assets/121341289/c1747d29-d77f-4037-b831-9e089ec3d2d1">

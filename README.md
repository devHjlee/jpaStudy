# jpaStudy
jpaStudy

# Log Query Parameter show Library : p6spy
https://github.com/gavlyukovskiy/spring-boot-data-source-decorator   
implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:${version}")

# 연관관계 정리



## JPA
### @Embedded , @Embeddable
*   회원(Member), 직원(Emp)테이블에 주소에 관한 동일한 컬럼을 사용하게 되는데 코드의 가독성,재사용을 위해 사용
*   Ex) Member 테이블에 zipCode, address1, address2 // Emp 테이블에 zipCode, address1, address2 사용시 Address 객체에 zipCode, address1,address2 생성하여 
Member.Emp Entity 에 @Embedded Address 로 변경

```java
public class Member(){
  ...
  private zipCode;
  private address1;
  private address2;
}
public class Emp(){
  ...
  private zipCode;
  private address1;
  private address2;
}


//변경
@Embeddalbe
public class Address(){
  private zipCode;
  private address1;
  private address2;
}
public class Member(){
  ...
  @Embedded
  Address address;
}
public class Emp(){
  ...
  @Embedded
  Address address;
}

```



### @Enumerated
*   EnumType ORDINAL
    * default 값이며 상태값을 숫자로 저장최초
    * 상태값을 READY,COMP(1,2)만 사용하다 중간에 다른 상태값 DEL 이 추가되면 READY,DEL,COMP (1,2,3)으로 밀리므로 STRING 사용을 추천
  * EnumType STRING
    * 상태값을 문자열로

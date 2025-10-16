# java-calculator-precourse

---

# 문자열 덧셈 계산기

## 기능 요구 사항

입력한 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.

* 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
    * 예: "" => 0, "1,2" => 3, "1,2,3" => 6, "1,2:3" => 6
* 앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
    * 예를 들어 "//;\n1;2;3"과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
* 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.

---

## 입출력 요구 사항

### 입력

구분자와 양수로 구성된 문자열

### 출력

* 덧셈 결과

``` commandline
결과 : 6
```

### 실행 결과 예시

``` commandline
덧셈할 문자열을 입력해 주세요.
1,2:3
결과 : 6
```

---

## 프로그래밍 요구 사항

* JDK 21 버전에서 실행 가능해야 한다.
* 프로그램 실행의 시작점은 `Application`의 `main()`이다.
* `build.gradle` 파일은 변경할 수 없으며, **제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.**
* 프로그램 종료 시 `System.exit()`를 호출하지 않는다.
* 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.
* 자바 코드 컨벤션을 지키면서 프로그래밍한다.
* 기본적으로 [Java Style Guide](https://github.com/woowacourse/woowacourse-docs/tree/main/styleguide/java)를 원칙으로 한다.

## 라이브러리

* `camp.nextstep.edu.missionutils`에서 제공하는 `Console` API를 사용하여 구현해야 한다.
* 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

---

## 기능 구현 목록

- [ ] Config
    - [X] Default Seperator
    - [X] Custom Seperator Prefix
    - [ ] Custom Seperator Suffix
- [ ] Controller
    - [ ] Run
- [ ] Input Parser
    - [ ] Parse String
    - [ ] Validate Input
- [ ] Model
    - [ ] Calculator
        - [ ] Add Numbers
- [X] View
    - [X] Get Input
    - [X] Print Text

---

## 기능 요구 사항 중 예외 처리

1. 음수 혹은 0 입력 시
    * 에러 처리 (IllegalArgumentException)
2. 구분자 사이 숫자가 비었거나 온전하지 않을 시
    * 에러 처리 (IllegalArgumentException)
3. 구분자 혹은 기타 문자가 첫 숫자 앞에 있을 시
    * 에러 처리 (IllegalArgumentException)
4. 커스텀 구분자 사용을 명시하였으나 비었을 시
    * 에러 처리 (IllegalArgumentException)
5. 커스텀 구분자 2개 이상 사용 시
    * 에러 처리 (IllegalArgumentException)
6. .(온점, 소수점)이 커스텀 구분자로 입력되었을 시
    * 자연수로만 간주하여 계산 진행
7. -(마이너스)가 커스텀 구분자로 입력되었을 시
    * 음수가 아닌 커스텀 구분자와 양수로 인식

## 기능 구현 전 고려 사항

1. 요구하는 기능의 복잡도가 높지 않아 Utils는 따로 만들지 않음
2. Console의 입출력이 UI이어서 Framework가 없는 수준이지만 이는 현업을 고려하여 배제함

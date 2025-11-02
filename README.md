# Woowa-precourse 3주차 (🎱 Lotto)

## 용어 정의

### 로또

| 용어            | 설명                                      |
|---------------|-----------------------------------------|
| Lotto         | 6개의 숫자로 구성되며, 당첨 기준에 따라 순위가 결정되는 로또     |
| Lotto Number  | 하나의 로또가 포함하는 숫자 (하나의 로또 안의 수는 중복될 수 없음) |
| Lotto Machine | 로또를 발행하는 기계 (어플리케이션에 하나만 존재)            |

### 순위

| 용어          | 설명                                 |
|-------------|------------------------------------|
| Rank        | 로또 당첨 순위                           |
| First Rank  | 1등, 6개 번호 일치, 2,000,000,000원       |
| Second Rank | 2등, 5개 번호 + 보너스 번호 일치, 30,000,000원 |
| Third Rank  | 3등, 5개 번호 일치, 1,500,000원           |
| Fourth Rank | 4등, 4개 번호 일치, 50,000원              |
| Fifth Rank  | 5등, 3개 번호 일치, 5,000원               |

### 당첨

| 용어                 | 설명                                     |
|--------------------|----------------------------------------|
| Winning            | 당첨을 뜻하는 용어                             |
| Winning Number     | 로또와 동일한 형태의 숫자 집합으로, 당첨 순위를 결정하는 기준이 됨 |
| Bonus Number       | 당첨 번호와 별개로, 2등을 결정하는 보너스 번호            |
| Winning Statistics | 1등부터 5등까지 각 등수별 당첨 개수를 집계한 결과          |
| Yield              | 수익률 (로또 구매 비용과 당첨 상금을 비교하여 산출되는 정보)    |

### 돈

| 용어    | 설명                                  |
|-------|-------------------------------------|
| Prize | 상금                                  |
| Price | 금액, 가격 (여기서는 로또의 가격을 표현하는 것에 사용)    |
| Money | 금액 연산을 담당하는 값 객체 (상금, 구매 금액 등에서 사용) |

### 판매원

| 용어      | 설명                 |
|---------|--------------------|
| Cashier | 돈을 받고 티켓을 발급하는 판매원 |

## 도메인

| 도메인               | 설명                                                       |
|-------------------|----------------------------------------------------------|
| Lotto             | 6개의 LottoNumber를 가지는 로또                                  |
| LottoNumber       | Lotto가 가지는 숫자 (1부터 45사이의 숫자)                             |
| LottoMachine      | 입력된 금액만큼 수량을 계산하여 그 수량만큼의 로또를 발행하는 객체                    |
| Cashier           | 사용자가 지불한 금액을 받고, LottoMachine을 통해 금액에 맞는 수량의 로또를 구매하는 객체 |
| Rank              | Lotto의 번호와 Winning Number를 비교하여 결정되는 당첨 순위               |
| WinningNumbers    | 6개의 LottoNumber와 1개의 BonusNumber를 같이 가지고 있으며 당첨의 기준이 됨   |
| WinningStatistics | 순위별 당첨 개수 정보를 가지는 당첨 통계 전송 객체                            |

## 기능 요구 사항

- [x] 로또(`Lotto`)는 6개의 숫자(`Lotto Number`)를 가진다.
    - [x] 로또 번호(`Lotto Number`)의 숫자 범위는 1~45까지이다.
    - [x] 1개의 로또(`Lotto`)를 발행할 때 중복되지 않는 6개의 숫자(`Lotto Number`)를 뽑는다.

- [x] 로또(`Lotto`)는 당첨(`Winning`)될 수 있다.
    - [x] 당첨 번호(`Winning Number`) 추첨 시 중복되지 않는 숫자 6개와 보너스 번호(`Bonus Number`) 1개를 뽑는다.
    - [x] 당첨 결과(`Winning Statistics`)는 1등부터 5등까지의 순위를 포함한다.
    - [x] 당첨(`Winning`) 기준과 상금 금액(`Money`)은 아래와 같다.
        - 1등(`First Rank`): 6개 번호(`Winning Number`) 일치 / 2,000,000,000원
        - 2등(`Second Rank`): 5개 번호(`Winning Number`) + 보너스 번호 일치 / 30,000,000원
        - 3등(`Third Rank`): 5개 번호(`Winning Number`) 일치 / 1,500,000원
        - 4등(`Fourth Rank`): 4개 번호(`Winning Number`) 일치 / 50,000원
        - 5등(`Fifth Rank`): 3개 번호(`Winning Number`) 일치 / 5,000원

- [x] 로또 기계(`LottoMachine`)는 로또(`Lotto`)를 발행한다.
    - [x] 로또 기계(`LottoMachine`)는 입력된 금액(`Price`)을 기준으로 구매 가능한 로또(`Lotto`)의 개수를 계산한다.
    - [x] 로또(`Lotto`) 구입 금액(`Price`)을 입력하면, 구입 금액(`Price`)에 해당하는 만큼 로또(`Lotto`)를 발행한다.
    - [x] 로또(`Lotto`) 1장의 가격은 1,000원이다.

- [x] 로또 당첨(`Winning`) 기준이 되는 번호들을 입력받는다.
    - [x] 당첨 번호(`Winning Number`)를 입력받는다.
    - [x] 보너스 번호(`Bonus Number`)를 입력받는다.

- [x] 당첨 결과(`Winning Statistics`)를 출력한다.
    - [x] 사용자가 구매한 로또 번호(`Lotto Number`)와 당첨 번호(`Winning Number`)를 비교한다.
    - [x] 당첨 내역(`Winning Statistics`)을 출력한다.
    - [x] 수익률(`Yield`)을 출력한다.
    - [x] 출력이 끝나면 로또 게임을 종료한다.

- [ ] 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨다.
    - [ ] "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - [ ] `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.

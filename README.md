# four-cuts-Backend
집에서 웹으로 인생네컷을 만들어보아요.

### 🔥 Git 작업 흐름

1. git checkout -b "feature/write-post-function"
2. 코드 작업
3. git add .
4. git push
5. 깃 사이트에서 풀 리퀘 날리기
6. 코드 리뷰 하고 Main에 머지하기
7. git checkout main
8. git pull
9. git checkout -b "feature/구현할 기능 설명 or feauture/페이지 설명"

### 🔥 Git Commit Convention

[양식]
type: subject

[예시]
feat: add key mapping


### Type **Rule**

| Type | 설명 |
| --- | --- |
| Feat: | 새로운 기능 추가 |
| Fix: | 버그 수정 또는 typo |
| Refactor: | 리팩토링 |
| Design: | CSS 등 사용자 UI 디자인 변경 |
| Comment: | 필요한 주석 추가 및 변경 |
| Style: | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |
| Test: | 테스트(테스트 코드 추가, 수정, 삭제, 비즈니스 로직에 변경이 없는 경우) |
| Chore: | 위에 걸리지 않는 기타 변경사항(빌드 스크립트 수정, assets image, 패키지 매니저 등) |
| Init: | 프로젝트 초기 생성 |
| Rename: | 파일 혹은 폴더명 수정하거나 옮기는 경우 |
| Remove: | 파일을 삭제하는 작업만 수행하는 경우 |

### **Subject Rule**

1. 최대 50글자 넘지 않기

2. 마침표 및 특수기호 사용x

3. 개조식 구문으로 작성 (간결하고 요점적인 서술)

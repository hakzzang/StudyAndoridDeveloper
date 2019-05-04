#### git --version : 깃의 현재 버전을 검사한다.
#### git config --global user.name MY_NAME : 나의 로컬 깃에 이름을 등록한다.
#### git config --global user.email MY_EMAIL : 나의 로컬 깃에 이메일을 등록한다.
#### git clone GIT_URL : GIT_URL을 갖고 있는 저장소를 로컬에 복사한다.READ

#### git init : 현재 경로에 깃을 사용할 수 있도록 초기화한다.

#### git status : 작업된 사항이 있는 지, 검사하는 명령어.  

#### git add FILE_PATH : 편집된 파일, 혹은 경로를 작업 영역으로 이동시킨다.

#### git commit -m "{COMMIT MESSAGE}" : 작업 영역에 있는 파일들, 묶음을 커밋 단위로 하여금 작업 사항을 메시지와 함께 등록한다.

#### git remote add [REMOTE_NAME] [https://github.com/~/~.git] : 외부의 저장소를 NAMING으로 하여금, 로컬에서 쉽게 경로를 가져온다. 해당 NAMING을 통해서 push를 할 때, 저장소를 지정한다.

#### git push -u [REMOTE_NAME] [BRANCH_NAME] : NAMING으로 등록한 저장소의 브런치에 해당 변경 작업사항을 이동시킨다.

#### git remote -v : 내가 갖고 있는 저장소의 이름과 url을 확인한다.

-----------------------------------------------------

#### git checkout -b BRANCH_NAME : BARNCH_NAME의 브랜치를 만들고, 이동합니다.

#### git checkout BRANCH_NAME : 브랜치를 BRANCH_NAME으로 이동합니다.

#### git branch : 레파지토리가 갖고 있는 브랜치의 목록을 보여줍니다.

#### git branch -d BRANCH_NAME : BRANCH_NAME의 브랜치를 삭제합니다. 만약, 현재 브랜치일 때, 해당 명령어를 사용해도 브랜치는 삭제가 안 됨.

#### git push [REMOTE_NAMAE] [BRNACH_NAME] : 현재 브랜치의 변경 내역을 이동시킵니다.
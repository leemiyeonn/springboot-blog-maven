let index={

    init:function(){
    $("#btn-save").on("click",()=>{
        this.save();
    });

    },

    save:function(){

        let data = {
        title: $("#title").val(),
        content: $("#content").val()
        };


        $.ajax({
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data), // http body data
            contentType:"application/json;charset=utf-8", // body data type (MIME)
            dataType:"json" // 응답 타입이 json 이라면 -> javascript 오브젝트로 변경
        }).done(function(resp){
            alert(" 글쓰기가 완료되었습니다. 🙌 ");
            console.log(resp);
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

    },

}

index.init();

/* $.ajax({
    type : `http method type`,
    url : `url`,
    data : `서버에 전송할 데이터`,
    contentType : "전송할 데이터 타입",
    //기본 값 : "application / x-www-form-urlencoded; charset = UTF-8"
    dataType : '서버로 부터 수신할 데이터 타입',
    //아무것도 지정하지 않으면 jQuery는 응답의 MIME 유형을 기반으로 해석을 시도
    error : `에러 발생시 수행할 함수`,
    success : `성공시 수행할 함수`
  });
*/
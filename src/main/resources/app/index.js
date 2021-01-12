var main= { //왜 이거 썼냐면 index로 scope를 한정시키기 위해 사용한다.중복되는거 있으면 브라우저의 scope는 공용이므로 난리가 난다.
    init : function (){
        var _this = this;
        $('#btn-save').on('click',function(){
            _this.save();
        })
        $('#btn-update').on('click',function(){
            _this.update();
        })
        $('#btn-delete').on('click',function(){
            _this.delete();
        })
    },
    save : function(){
        var data ={
            title : $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: 'POST',
            url: '/portfolio',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    },
    update : function(){
        var data ={
            title: $('#title').val(),
            content: $('#content').val()
        };
        var id = $('#id').val();

        $.ajax({
            type:'PUT',
            url : '/portfolio/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href ='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    delete : function(){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/portfolio/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();
var main= { //왜 이거 썼냐면 index로 scope를 한정시키기 위해 사용한다.중복되는거 있으면 브라우저의 scope는 공용이므로 난리가 난다.
    init : function (){
        var _this = this;
        $('#kospi-save').on('click',function(){
            _this.kospi_save();
        }),
        $('#kospi-update').on('click',function(){
            _this.kospi_update();
        })
    },
    kospi_save : function(){
        $.ajax({
            type: 'GET',
            url: '/admin/save/kospi'
        }).done(function(){
            alert('코스피가 저장되는 중입니다');
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    },
    kospi_update : function(){
        $.ajax({
            type: 'GET',
            url: '/admin/update/kospi'
        }).done(function(){
            alert('코스피가 갱신되는 중입니다');
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    }
};

main.init();
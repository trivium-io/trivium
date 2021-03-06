var path = $('#commits').data('path');
var url = 'https://api.github.com/repos/trivium-io/trivium/commits?path='+path;
$.ajax({type:'GET',
        url:url,
        success: function(data){
    var str="<table class='docutils'><thead><tr><th>message</th><th>date</th><th>author</th><th>link</th></tr></thead><tbody>";
    for(var idx=0;idx<data.length;idx++){
      var one = data[idx];
      var d = one.commit.author.date.substr(0,10);
      var t = one.commit.author.date.substr(11,10);
      str+="<tr><td>"+one.commit.message+"</td><td>"
          +d+" "+t+"</td><td>"
          +one.commit.author.name+"</td><td>"
          +"<a href='"+one.html_url+"'>"+one.sha.substr(0,7)+"</a></td></tr>";
    }
    str+="</tbody></table>";
    $('#commits').html(str);
}});

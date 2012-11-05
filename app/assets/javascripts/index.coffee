$ ->
  $.get "/listBars", (data) ->
    $.each data, (index, item) ->
      $("#bars").append "<li>" + index + " " + item.name + "</li>"

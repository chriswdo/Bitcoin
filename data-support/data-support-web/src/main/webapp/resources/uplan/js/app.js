(function($) {
  'use strict';

  $(function() {
    var $fullText = $('.admin-fullText');
    $('#admin-fullscreen').on('click', function() {
      $.UPUI.fullscreen.toggle();
    });

    $(document).on($.UPUI.fullscreen.raw.fullscreenchange, function() {
      $.UPUI.fullscreen.isFullscreen ? $fullText.text('关闭全屏') : $fullText.text('开启全屏');
    });
  });
})(jQuery);

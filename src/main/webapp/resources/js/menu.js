(function($) {
	$(document).ready(
			function() {
				var url = window.location;

				var page = window.location.pathname;
                
				if(page === "/<NOME-APLICAÇÃO>/") {
				    page += "dashboard.xhtml";
				}
			
				$('ul.sidebar-menu a[href="' + page + '"]')
						.parent().addClass('active');

				$('ul.sidebar-menu a').filter(function() {
					return this.href == url;
				}).parent().parent().parent().addClass('active');
			});
})(jQuery);

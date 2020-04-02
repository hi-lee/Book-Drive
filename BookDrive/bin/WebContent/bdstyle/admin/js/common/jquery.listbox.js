/*
 * jListbox jQuery plugin
 *
 * Copyright (c) 2009 Giovanni Casassa (senamion.com - senamion.it)
 *
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * http://www.senamion.com
 *
 */

jQuery.fn.listbox = function(o) {

	o = jQuery.extend({
		selectText: "No option",
		viewText: true
	}, o);
	
	return this.each(function () {
		var el = $(this);

		name = (el.attr('name') || el.attr('id') || 'internalName');
		
		$("#" + name + "> a").click(function() {
			if ($(this).next().css("visibility") == "hidden") {
				$(".list").css("visibility", "hidden");
				$(this).next().css("visibility", "visible")
			}
			else {
				$(this).next().css("visibility", "hidden");
			}
			return false;
		});

		/*$("#" + name + " ul li").click(function() {
			listName = $(this).parent().parent().attr('id');
			listName = listName.substr(0, listName.length - 4);
			$('[name=' + listName + ']').val($(this).attr("rel"));
			$(this).parent().parent().children().eq(0).html($(this).html());
		});*/

		$().click(function() {
			$(".list").css("visibility", "hidden");
		});
	});
	
};

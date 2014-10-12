	$(function(){
		$("#tree").dynatree({
			checkbox: true,
			
			// Override class name for checkbox icon, so rasio buttons are displayed:
			//classNames: {checkbox: "dynatree-radio"},
			// Select mode 3: multi-hier
			selectMode: 3,
			children: [
				{title: "Folder 1", isFolder: true,key: "node1",expand: true,
					children: [
						{title: "Sub-item 1.1", key: "node1.1"},
						{title: "Sub-item 1.2", key: "node1.2"}
					]
				},
				{title: "Folder 2", isFolder: true, key: "node2",expand: true,
					children: [
						{title: "Sub-item 2.1", key: "node2.1"},
						{title: "Sub-item 2.2", key: "node2.2"}
					]
				},
				{title: "Folder 3", isFolder: true, key: "node3",expand: true,
					children: [
						{title: "Sub-item 3.1", key: "node3.1"},
						{title: "Sub-item 3.2", key: "node3.2"}
					]
				}
			]
		});
		$("form").submit(function() {
			// Serialize standard form fields:
			var formData = $(this).serializeArray();

			// then append Dynatree selected 'checkboxes':
			var tree = $("#tree").dynatree("getTree");
			formData = formData.concat(tree.serializeArray());

			// and/or add the active node as 'radio button':
			if(tree.getActiveNode()){
				formData.push({name: "activeNode", value: tree.getActiveNode().data.key});
			}

			alert("POSTing this:\n" + jQuery.param(formData));

			$.post("http://127.0.0.1:8001/submit_data",
				   formData,
				   function(response, textStatus, xhr){
					   alert("POST returned " + response + ", " + textStatus);
				   }
			);
			return false;
		});
	});

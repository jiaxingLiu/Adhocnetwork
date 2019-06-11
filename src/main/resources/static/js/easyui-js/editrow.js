/**
 * Created by Administrator on 2017/11/9 0009.
 */
var editIndex = undefined;
function endEditing(name){
    if (editIndex == undefined){return true}
    if ($(name).datagrid('validateRow', editIndex)){
        $(name).datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
};
function medium(ele){ //删除按钮点击去触发父元素的点击事件，以便执行onClickCell .根据value执行删除动作。
    $(ele).parent(".datagrid-cell").click();
};
function onClickCell(index, field,value){
    var localName = "#"+this.id;
    action.localDatagridName = localName;
    if(value == "true"){
        removeIndex(localName,index);
    }else{
        if(editIndex != index){
            if(endEditing(localName)){
                $(localName).datagrid('selectRow', index)
                    .datagrid('beginEdit', index);
                var ed = $(localName).datagrid('getEditor', {index:index,field:field});
                if(ed){
                    ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                }
                editIndex = index;
            }else{
                setTimeout(function(){
                    $(localName).datagrid('selectRow', editIndex);
                },0);
            }
        }
    };
};
function onEndEdit1(index, row){
    var ed = $(this).datagrid('getEditor', {
        index: index,
        field: 'numberId'
    });
    var ed2 = $(this).datagrid('getEditor', {
        index: index,
        field: 'userId'
    });
    row.sampleName = $(ed.target).combobox('getText');
    row.username = $(ed2.target).combobox('getText');
    row.userId = $(ed2.target).combobox('getValue');
    row.userName = row.username;
};
function onEndEdit(index, row){
    var ed = $(this).datagrid('getEditor', {
        index: index,
        field: 'userId'
    });
    row.username = $(ed.target).combobox('getText');
    row.userName = row.username;
};
function onEndEditMore(index, row){
    var ed = $(this).datagrid('getEditor', {
        index: index,
        field: 'usePersonId'
    });
    var ed2 = $(this).datagrid('getEditor', {
        index: index,
        field: 'provePersonId'
    });
    var ed3 = $(this).datagrid('getEditor', {
        index: index,
        field: 'checkPersonId'
    });
    row.username = $(ed.target).combobox('getText');
    row.username2 = $(ed2.target).combobox('getText');
    row.username3 = $(ed3.target).combobox('getText');
};

function append(name){
	console.log(name);
    if(action.localDatagridName && action.localDatagridName != name) {
        endEditing(action.localDatagridName);
    };
    action.localDatagridName = name;
    if (endEditing(name)){
        $(name).datagrid('appendRow',{cz:'true'});
        editIndex = $(name).datagrid('getRows').length-1;
        $(name).datagrid('selectRow', editIndex)
            .datagrid('beginEdit', editIndex);
    }
};
function removeit(){
    if (editIndex == undefined){return}
    $('#dg').datagrid('cancelEdit', editIndex)
        .datagrid('deleteRow', editIndex);
    editIndex = undefined;
};
function removeIndex(name,index){
    if (editIndex == index){
        $(name).datagrid('cancelEdit', index)
            .datagrid('deleteRow', index);
        editIndex = undefined;
    }else{
        $(name).datagrid('deleteRow', index);
    };
};
function accept(){
    if (endEditing()){
        $('#dg').datagrid('acceptChanges');
    }
};
function reject(){
    $('#dg').datagrid('rejectChanges');
    editIndex = undefined;
};
function getChanges(){
    var rows = $('#dg').datagrid('getChanges');
    alert(rows.length+' rows are changed!');
};
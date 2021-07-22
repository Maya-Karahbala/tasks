let teststr=`<table><tbody>
<tr style="background-color: red">
    <td>Donna</td>
    <td>2016-10-01</td>
    <td></td>
</tr>
<tr style="background-color: red">
    <td>Nansen</td>
    <td>2016-11-05</td>
    <td>2016-11-07</td>
</tr>
<tr style="background-color: red">
    <td>Peppe</td>
    <td>2016-11-05</td>
    <td>2016-11-14</td>
</tr>
<tr>
    <td>J'Sira</td>
    <td>2012-02-28</td>
    <td>2012-03-14</td>
</tr>
<tr>
    <td>Billie</td>
    <td>2015-08-14</td>
    <td>2015-10-09</td>
</tr>
<tr>
    <td>Peyton</td>
    <td>2015-08-14</td>
    <td>2015-10-09</td>
</tr>
</tbody></table>


`
function filterTableString(tableString){
    let tableStr
    tableStr=tableString.replace("<table>","")
    tableStr=tableStr.replace("</table>","")
    tableStr=tableStr.replace("<tbody>","")
    tableStr=tableStr.replace("</tbody>","")
    tableStr=tableStr.replace(/(?:\r\n|\r|\n)/g, '');
    return tableStr
}
function getTableRowsStr(tableString){
    let tableStr= filterTableString(tableString)
    let rowsStrings = tableStr.split("</tr>");
    rowsStrings=rowsStrings.filter(el=>{
        if(el.rowsStrings>1) {
        return el
    }})
    //console.log(myArr)
    return myArr

}
function isRed(rowStr){
    if(rowStr.includes("style")){
        // if there is style then celll is red
        return true
    }
    return false


}
function getCellData(rowStr){
    let str=strWithoutStyle(rowStr)
    //console.log(str)
    //console.log("---------")
    if(!str.includes("<td>")){
        return str
    }
    let start=str.indexOf("<td>")+3

    return str.slice(start)


}
function getRowCells(rowStr){
    return rowStr.split("</td>").filter(el=>{
        if(el.length>1) {
        return el
    }})


}
function strWithoutStyle(str){
    let cutIndex=str.indexOf(">")  
    return str.slice(cutIndex+1)
}
function getData(table){
    let rows=getTableRowsStr(table)
    let allData=[]
    rows.forEach(element => {
        let rowCells= getRowCells(element)
        let data=[]
        rowCells.map(el=>{
            //console.log(getCellData(el))
            data.push(getCellData(el))
        })
        data.push(isRed(element)) 
        allData.push(data)   
    });  
    
    return(allData)
} 
function isoverdue(dataArray,limit,today){
    let borrawDate=new Date(dataArray[1])
    let lastDate=dataArray[2]==""? new Date(today):new Date(dataArray[2])
    //var diffDays = lastDate.getDate() - borrawDate.getDate(); 
    var Difference_In_Time = lastDate.getTime() - borrawDate.getTime();
  
// To calculate the no. of days between two dates
var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
    return limit>Difference_In_Days
   

}

function getWrongCount(teststr,limit, today){
    let tableData=getData(teststr)
    //console.log(tableData)
    let count=0
    tableData.map(row=> {

        let isRed=row[3]
        let isoverdued=isoverdue(row,limit,today)
        //console.log(isRed,isoverdued)
        if(!isRed || !isoverdued){
            count=count+1
        }
    })
    return count
}
console.log(getWrongCount(teststr,7, "2016-10-10"))




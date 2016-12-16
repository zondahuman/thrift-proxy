include "OperateType.thrift" 
namespace java com.abin.lee.thrift.model
 
struct HelloInfo{
1:i32 id,
2:string greetings,
3:map<i32,string> words,
4:OperateType.OperateType operateType
}
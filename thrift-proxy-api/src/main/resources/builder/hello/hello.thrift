include "HelloInfo.thrift" 
include "HelloException.thrift" 
namespace java com.abin.lee.thrift.api




service  HelloService {
  string sayHello(1:string userName) throws (1:HelloException.HelloException e);
  HelloInfo.HelloInfo findHelloInfo(1:i32 id) throws (1:HelloException.HelloException e);
  void apply(1:string userName, 2:i32 threshold) throws (1:HelloException.HelloException e);
}
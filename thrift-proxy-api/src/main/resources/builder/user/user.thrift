namespace java com.abin.lee.thrift.api
 
service  UserService {
  string sayUser(1:string userName)
  list<i32> find(1:string userName)
  list<string> findUser(1:i32 age)
  map<i32,string> findUser1(1:i32 age)
  map<string,string> findUser2(1:i32 age)
  map<string,list<i32>> findUser3(1:i32 age)
  set<list<i32>> findUser4(1:binary id)
  binary findUser5(1:i32 id)

}
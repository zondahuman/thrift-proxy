# thrift-proxy
Support for integrate Spring, Curator in Thrift

一、This Application includes Two Parts(Step One:Thrift Server, Step Two:Thrift Client):

Step One:(Thrift Server)

this part include the following:

1、thrift-svr-api (include XXXXXXService.Iface)

2、thrift-svr-common (Include Server Register and Server Discover which use Curator)

3、thrift-svr-service (Implementation Class For XXXXXXService.Iface)

Step Two:(Thrift Client)

1、thrift-svr-client (Call XXXXXXService.Iface , It achieve some functions that It can Call Automatically By Annotation).


二、How to use it?

First:

    Run thrift-svr-service by tomcat.

Second:

    Run thrift-svr-client  by tomcat.

三、Calling order

Http(thrift-svr-client)--------------->Thrift RPC Client(thrift-svr-client)------------->Thrift RPC Service(thrift-svr-service)

1、thrift-svr-client Provider Http Service By SpringMVC Controller

2、Http Service Calls Thrift RPC Client

3、Thrift RPC Client Calls Automatically Thrift RPC Service Which achieve functions That By Meaning Of Java Reflections By The Method Name.

四、Registry Center

1、Thrift RPC Service(thrift-svr-service) register to the zookeeper as RPC Service Provider.

2、Thrift RPC Client(thrift-svr-client) pull RPC Service Provider Service Lists(includes IP:PORT:WEIGHT) that conenct to zookeeper.

3、Thrift RPC Client request Thrift RPC Service By IP And Port which from The Above Step.


The Appendix:

The Following is The Environment For Me:

1、JDK1.8

2、Tomcat8.0

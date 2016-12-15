# thrift-proxy
Support for integrate Spring, Curator in Thrift

This Application includes Two Parts(Step One:Thrift Server, Step Two:Thrift Client):

Step One:(Thrift Server)

this part include the following:

1、thrift-svr-api (include XXXXXXService.Iface)

2、thrift-svr-common (Include Server Register and Server Discover which use Curator)

3、thrift-svr-service (Implementation Class For XXXXXXService.Iface)

Step Two:(Thrift Client)

1、thrift-svr-client (Call XXXXXXService.Iface , It achieve some functions that It can Call Automatically By Annotation).


How to use it?

First:

    Run thrift-svr-service by tomcat.

Second:

    Run thrift-svr-client  by tomcat.


The Appendix:

The Following is The Environment For Me:

1、JDK1.8

2、Tomcat8.0

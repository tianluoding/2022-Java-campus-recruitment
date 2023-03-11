[toc]

# 目录

```
/ 根目录 
/bin 存放必要的命令 
/boot 存放内核以及启动所需的文件
/dev 存放设备文件 
/etc 存放系统配置文件 
/home 普通用户的宿主目录，用户数据存放在其主目录中 
/lib 存放必要的运行库 
/mnt 存放临时的映射文件系统，通常用来挂载使用。
 /proc 存放存储进程和系统信息 
/root 超级用户的主目录 
/sbin 存放系统管理程序 
/tmp 存放临时文件
/usr 存放应用程序，命令程序文件、程序库、手册和其它文档。 
/var 系统默认日志存放目录
```


# linux下不同颜色文件的含义

![image](E:/youdaoyun/linux下不同颜色文件代表的含义.png)
 
 
# 查端口号： sudo netstat -antup

```
-a (all)显示所有选项，默认不显示LISTEN相关
-t (tcp)仅显示tcp相关选项
-u (udp)仅显示udp相关选项
-n 拒绝显示别名，能显示数字的全部转化成数字。
-l 仅列出有在 Listen (监听) 的服務状态

-p 显示建立相关链接的程序名
-r 显示路由信息，路由表
-e 显示扩展信息，例如uid等
-s 按各个协议进行统计
-c 每隔一个固定时间，执行该netstat命令。

提示：LISTEN和LISTENING的状态只有用-a或者-l才能看到
```


# 查看进程运行： ps -aux
```
下面对命令选项进行说明：
　　-e 显示所有进程。
　　-f 全格式。
　　-h 不显示标题。
　　-l 长格式。
　　-w 宽输出。
　　a 显示终端上的所有进程，包括其他用户的进程。
　　r 只显示正在运行的进程
　　-u 以用户为主的进程状态
```

# 创建用户目录和设置密码

- r：建立系统账号
- m：自动建立用户的登入目录
- s：指定用户登入后所使用的shell
 
创建用户
```
sudo useradd -r -m -s /bin/bash chenjie
```
设置密码
```
sudo passwd chenjie
```


# 磁盘管理： du命令参数
```
-a  显示所有目录或文件的大小
-b  以byte为单位，显示目录或文件的大小
-c  显示目录或文件的总和
-k  以KB为单位输出
-m  以MB为单位输出
-s  仅显示目录或文件的总计数值
-h  以K,M,G为单位，提高信息可读性
-x  跳过不同的文件系统目录
-S  显示目录的大小，但不含子目录大小。
-D  显示指定符号链接的源文件大小
```


## 看磁盘占用情况 : sudo du -sh /*
```
ccpang@ubuntu:~$ sudo du -sh /*
[sudo] password for ccpang: 
13M	/bin
167M	/boot
4.0K	/cdrom
0	/dev
15M	/etc
20G	/home
0	/initrd.img
0	/initrd.img.old
1.2G	/lib
5.8M	/lib32
4.0K	/lib64
16K	/lost+found
12K	/media
1.8G	/mnt
143M	/opt
du: cannot access '/proc/12269/task/12269/fd/4': No such file or directory
du: cannot access '/proc/12269/task/12269/fdinfo/4': No such file or directory
du: cannot access '/proc/12269/fd/3': No such file or directory
du: cannot access '/proc/12269/fdinfo/3': No such file or directory
0	/proc
44K	/root
du: cannot access '/run/user/1000/gvfs': Permission denied
2.1M	/run
12M	/sbin
4.7G	/snap
4.0K	/srv
1.9G	/swapfile
0	/sys
204K	/tmp
6.9G	/usr
4.8G	/var
0	/vmlinuz
0	/vmlinuz.old

```

##  排序显示
```
sudo du -s /usr/lib/* | sort -nr

```


[toc]

# vmstat参数介绍
vmstat命令：  用来获得有关进程、虚存、页面交换空间及 CPU活动的信息。这些信息反映了系统的负载情况.

vmstat工具的使用是通过两个数字来完成的，一个是采用的时间间隔，单位是秒；另一个是采样的次数。

##  vmstat举例
```
ccpang@ubuntu:~$ vmstat 2
procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 0  0      0 263700 263540 1656064    0    0    29    18  148  388  0  0 99  0  0
 0  0      0 263692 263540 1656064    0    0     0     0  153  422  2  0 98  0  0
 0  0      0 263692 263540 1656064    0    0     0     0  148  406  2  1 98  0  0
 1  0      0 263692 263540 1656064    0    0     0     0  145  402  1  0 99  0  0
 1  0      0 263692 263540 1656064    0    0     0     0  144  402  2  0 98  0  0
 0  0      0 263692 263540 1656064    0    0     0     0  148  432  3  1 96  0  0

```
## vmstat结果分析
```
- r:表示运行队列
- b：表示阻塞的进程
- swpd：表示虚拟内存已经使用的大小
- free：空闲的物理内存的大小
- buff: 缓存
- cache:打开的文件，给文件做缓冲。
- si:每秒从磁盘读入的虚拟内存的大小
- so：表示从虚拟内存写入磁盘的大小
- bi:块设备每秒接收的数量
- bo: 块设备每秒发送的块数量
- in:每秒CPU的中断次数
- cs：每秒上下文切换次数
- us:用户cpu时间
- sy:系统CPU时间
- id:空闲CPU时间
- wa: 等待IO CPU时间

```
[toc]
监控CPU性能一般包括：运行队列、CPU使用率和上下文切换
对于一个CPU来说，运行队列最好不超过。

# top
## 命令功能
显示当前系统正在执行的进程的相关信息，包括进程ID、内存占用率、CPU占用率等
## 命令参数
```
-b 批处理模式显示程序信息，搭配 "n" 参数一起使用，可以用来将 top 的结果输出到档案内
-c 显示整个命令行而不只是显示命令名
-s 使top命令在安全模式运行，这将去除交互命令带来的危险。
-S 累积模式
-i 使top命令不显示任何闲置或者僵死进程
-u<用户名> 指定用户名
-p<进程号> 指定进程
-n<次数> 循环显示的次数
```
## 举例说明
```
ccpang@ubuntu$ top -bn 1 -i -c
top - 03:56:40 up 12:18,  1 user,  load average: 0.11, 0.09, 0.03
Tasks: 320 total,   1 running, 254 sleeping,   0 stopped,   0 zombie
%Cpu(s):  0.3 us,  0.4 sy,  0.1 ni, 99.3 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
KiB Mem :  4002232 total,   265100 free,  1818128 used,  1919004 buff/cache
KiB Swap:  1942896 total,  1942896 free,        0 used.  1873368 avail Mem 

   PID USER      PR  NI    VIRT    RES    SHR S %CPU %MEM     TIME+ COMMAND
  6831 ccpang    20   0   51340   4144   3368 R 10.0  0.1   0:00.04 top -bn 1 -i -c
  3988 ccpang    20   0 3020468 292932 106928 S  5.0  7.3   0:38.87 /usr/bin/gnome-shell
  4499 ccpang    20   0  728896  37696  27416 S  5.0  0.9   0:02.68 /usr/lib/gnome-terminal/gnome-terminal-server

```
## top结果说明
### 系统整体统计信息

#### 第一行：任务队列信息
- 03：56：40： 当前时间
- up 12:18： 已经运行了12时18分
- 1 user :当前有1个用户登录
- load average：分别是1分钟、5分钟、10分钟的负载情况。

#### 第二行：任务(进程)
系统现在有320个进程，1个运行，254个休眠、0个停止，0个僵死(zombie)
#### 第三行：CPU占用
- 0.3%us：用户空间占用CPU的百分比
- 0.4%sy:内核空间占用CPU的百分比
- 0.1%ni:改变过优先级的进程占用的CPU百分比
- 99.3%id:空闲CPU的占比
- 0.0%wa：IO等待占用CPU的百分比
- 0.0%hi:硬中断占用CPU的百分比
- 0.0%si:软中断占用的CPU百分比

#### 第四行：内存状态(单位kib)
- 4002232 total ：物理内存总量3.81G
- 265100 free： 剩余的内存总量(258M)
- 1818128 used：使用的内存总量(1.73G)
- 1919004 buff/cache：用作内核缓存的内存量(1.83G)

> buffer是即将被写入磁盘的，而cache是被从磁盘中读出来的。buffer是用于存储速度不同步的设备或优先级不同的设备之间传输数据的区域。cache经常用于磁盘的I/O请求上，如果有多个进程都要访问某个文件，于是该文件就要被做成cache以便下次访问。

#### 第五行：swap交换分区信息(单位kib)
- 1942896 total：交换区总量(1.85G)
- 1942896 free: 剩余的交换区总量(1.85G) 
- 0 used：空闲的交换区分量(0G)
- 1873368 avail Mem： 缓冲区的交换区总量(1.78G)

>avail Mem:表示可用于进程下一次分配的物理内存数量。

> 我们在观察内存使用情况时，只要没发现用swap的交换空间，就不必担心自己的内存太少。

### 进程信息区

```
  PID USER      PR  NI    VIRT    RES    SHR S %CPU %MEM     TIME+ COMMAND
  6831 ccpang    20   0   51340   4144   3368 R 10.0  0.1   0:00.04 top -bn 1 -i -c
  3988 ccpang    20   0 3020468 292932 106928 S  5.0  7.3   0:38.87 /usr/bin/gnome-shell
  4499 ccpang    20   0  728896  37696  27416 S  5.0  0.9   0:02.68 /usr/lib/gnome-terminal/gnome-terminal-server

```
- PID：进程ID
- USER:进程所有者的用户ID
- PR：优先级
- NI：nice值，负值表示高优先级、正值表示低优先级
- VIRT：进程使用的虚拟内存总量，单位是kb,VIRT=SWAP+RES
- RES：进程使用的、未被换出的物理内存大小，单位kb。RES=CODE+DATA
- SHR:共享内存大小，单位是kb
- S：进程状态 D=不可中断的睡眠状态、R=运行、S=睡眠、T=跟踪/停止、Z=僵尸进程
- %CPU：上次更新到现在的CPU时间占用百分比
- %MEM: 进程使用的物理内存百分比
- TIME+：进程使用的CPU时间总计，单位是1/100秒
- COMMARD：命令行


# 命令中的符号
## "&"表示任务在后台执行.
```
如果在后台运行redis-server ,则有redis-server &
```

## "&&"表示前一条命令执行成功，才执行一条命令。

```
echo '1‘ && echo '2'   
```


## "|"" 表示管道,上一条命令的输出，作为下一条命令参数.

```
echo 'yes' | wc -l

ps -ef | grep java      //检查java进程是否存在

```
## "||"表示上一条命令执行失败后，才执行下一条命令
```
cat nofile || echo "fail"

```

# kill 

## 命令格式：
```
kill[参数][进程号] 

```
## 命令功能：

发送指定的信号到相应进程。不指定型号将发送SIGTERM（15）终止指定进程。如果任无法终止该程序可用“-KILL” 参数，其发送的信号为SIGKILL(9) ，将强制结束进程，使用ps命令或者jobs 命令可以查看进程号。root用户将影响用户的进程，非root用户只能影响自己的进程。

## 命令参数：
```
-l  信号，若果不加信号的编号参数，则使用“-l”参数会列出全部的信号名称
-a  当处理当前进程时，不限制命令名和进程号的对应关系
-p  指定kill 命令只打印相关进程的进程号，而不发送任何信号
-s  指定发送信号
-u  指定用户 
```

## 注意：

1、kill命令可以带信号号码选项，也可以不带。如果没有信号号码，kill命令就会发出终止信号(15)，这个信号可以被进程捕获，使得进程在退出之前可以清理并释放资源。也可以用kill向进程发送特定的信号。例如：

kill -2 123

它的效果等同于在前台运行PID为123的进程时按下Ctrl+C键。但是，普通用户只能使用不带signal参数的kill命令或最多使用-9信号。

2、kill可以带有进程ID号作为参数。当用kill向这些进程发送信号时，必须是这些进程的主人。如果试图撤销一个没有权限撤销的进程或撤销一个不存在的进程，就会得到一个错误信息。

3、可以向多个进程发信号或终止它们。

4、当kill成功地发送了信号后，shell会在屏幕上显示出进程的终止信息。有时这个信息不会马上显示，只有当按下Enter键使shell的命令提示符再次出现时，才会显示出来。

5、应注意，信号使进程强行终止，这常会带来一些副作用，如数据丢失或者终端无法恢复到正常状态。发送信号时必须小心，只有在万不得已时，才用kill信号(9)，因为进程不能首先捕获它。要撤销所有的后台作业，可以输入kill 0。因为有些在后台运行的命令会启动多个进程，跟踪并找到所有要杀掉的进程的PID是件很麻烦的事。这时，使用kill 0来终止所有由当前shell启动的进程，是个有效的方法。


## 使用实例：

实例1：列出所有信号名称
```

命令：  kill -l

[root@localhost test6]# kill -l

输出：
 1) SIGHUP       2) SIGINT       3) SIGQUIT      4) SIGILL
 5) SIGTRAP      6) SIGABRT      7) SIGBUS       8) SIGFPE
 9) SIGKILL     10) SIGUSR1     11) SIGSEGV     12) SIGUSR2
13) SIGPIPE     14) SIGALRM     15) SIGTERM     16) SIGSTKFLT
17) SIGCHLD     18) SIGCONT     19) SIGSTOP     20) SIGTSTP
21) SIGTTIN     22) SIGTTOU     23) SIGURG      24) SIGXCPU
25) SIGXFSZ     26) SIGVTALRM   27) SIGPROF     28) SIGWINCH
29) SIGIO       30) SIGPWR      31) SIGSYS      34) SIGRTMIN
35) SIGRTMIN+1  36) SIGRTMIN+2  37) SIGRTMIN+3  38) SIGRTMIN+4
39) SIGRTMIN+5  40) SIGRTMIN+6  41) SIGRTMIN+7  42) SIGRTMIN+8
43) SIGRTMIN+9  44) SIGRTMIN+10 45) SIGRTMIN+11 46) SIGRTMIN+12
47) SIGRTMIN+13 48) SIGRTMIN+14 49) SIGRTMIN+15 50) SIGRTMAX-14
51) SIGRTMAX-13 52) SIGRTMAX-12 53) SIGRTMAX-11 54) SIGRTMAX-10
55) SIGRTMAX-9  56) SIGRTMAX-8  57) SIGRTMAX-7  58) SIGRTMAX-6
59) SIGRTMAX-5  60) SIGRTMAX-4  61) SIGRTMAX-3  62) SIGRTMAX-2
63) SIGRTMAX-1  64) SIGRTMAX

说明：只有第9种信号(SIGKILL)才可以无条件终止进程，其他信号进程都有权利忽略。 下面是常用的信号：

HUP    1    终端断线
INT     2    中断（同 Ctrl + C）
QUIT    3    退出（同 Ctrl + \）
TERM   15    终止
KILL    9    强制终止
CONT   18    继续（与STOP相反， fg/bg命令）
STOP    19    暂停（同 Ctrl + Z）
```
 

实例2：得到指定信号的数值

命令：
输出：
```
[root@localhost test6]# kill -l KILL
9
[root@localhost test6]# kill -l SIGKILL
9
[root@localhost test6]# kill -l TERM
15
[root@localhost test6]# kill -l SIGTERM
15
```

实例3：先用ps查找进程，然后用kill杀掉

```
命令：

kill 3268

输出：

[root@localhost test6]# ps -ef|grep vim 
root      3268  2884  0 16:21 pts/1    00:00:00 vim install.log
root      3370  2822  0 16:21 pts/0    00:00:00 grep vim

[root@localhost test6]# kill 3268 
[root@localhost test6]# kill 3268 
-bash: kill: (3268) - 没有那个进程
```
实例4：彻底杀死进程

命令：

kill –9 3268 

输出：
```
[root@localhost test6]# ps -ef|grep vim 
root      3268  2884  0 16:21 pts/1    00:00:00 vim install.log
root      3370  2822  0 16:21 pts/0    00:00:00 grep vim
[root@localhost test6]# kill –9 3268 
[root@localhost test6]# kill 3268 
-bash: kill: (3268) - 没有那个进程
```
实例5：杀死指定用户所有进程

命令：
```
kill -9 $(ps -ef | grep peidalinux)
kill -u peidalinux

输出：

[root@localhost ~]# kill -9 $(ps -ef | grep peidalinux) 
[root@localhost ~]# kill -u peidalinux
```

实例6：init进程是不可杀的

```
命令：

kill -9 1

输出：

[root@localhost ~]# ps -ef|grep init
root         1     0  0 Nov02 ?        00:00:00 init [3]                  
root     17563 17534  0 17:37 pts/1    00:00:00 grep init
[root@localhost ~]# kill -9 1
[root@localhost ~]# kill -HUP 1
[root@localhost ~]# ps -ef|grep init
root         1     0  0 Nov02 ?        00:00:00 init [3]                  
root     17565 17534  0 17:38 pts/1    00:00:00 grep init
[root@localhost ~]# kill -KILL 1
[root@localhost ~]# ps -ef|grep init
root         1     0  0 Nov02 ?        00:00:00 init [3]                  
root     17567 17534  0 17:38 pts/1    00:00:00 grep init
[root@localhost ~]#
说明：
init是Linux系统操作中不可缺少的程序之一。所谓的init进程，它是一个由内核启动的用户级进程。内核自行启动（已经被载入内存，开始运行，并已初始化所有的设备驱动程序和数据结构等）之后，就通过启动一个用户级程序init的方式，完成引导进程。所以,init始终是第一个进程（其进程编号始终为1）。 其它所有进程都是init进程的子孙。init进程是不可杀的！
```

#  split命令 

Linux split命令用于将一个文件分割成数个。该指令将大文件分割成较小的文件，在默认情况下将按照每1000行切割成一个小文件。

## 语法

```
split [--help][--version][-<行数>][-b <字节>][-C <字节>][-l <行数>][要切割的文件][输出文件名]
```

## 参数说明

```
    -<行数> : 指定每多少行切成一个小文件
    -b<字节> : 指定每多少字节切成一个小文件
    --help : 在线帮助
    --version : 显示版本信息
    -C<字节> : 与参数"-b"相似，但是在切 割时将尽量维持每行的完整性
    [输出文件名] : 设置切割后文件的前置文件名， split会自动在前置文件名后再加上编号
```


## 实例

```
1).使用指令"split"将文件"README"每6行切割成一个文件，输入如下命令：

$ split -6 README       #将README文件每六行分割成一个文件 

以上命令执行后，指令"split"会将原来的大文件"README"切割成多个以"x"开头的小文件。而在这些小文件中，每个文件都只有6行内容。

使用指令"ls"查看当前目录结构，如下所示：
$ ls                                #执行ls指令  

#获得当前目录结构  
README xaa xad xag xab xae xah xac xaf xai    



2).将date.file 分成每个大小10k的文件,文件名前缀：spilt_file，文件名后缀： -d——使用数字 ， -a——限定3位
split -b 10k date.file -d -a 3 split_file


3).将date.file按每个12行分割i
split -l 12 date.file
split -l 12 date.file -d -a 3 split_file

ls：
date.file split_file000 split_file001 split_file002 split_file003 split_file004 split_file005 split_file006 split_file007 split_file008 split_file009
```

 

# chown 


 chown:改变某个文件或者目录的所有者和所属的组，该命令可以向某个用户授权，使该用户变成指定文件的所有者
或者改变文件所属的组。用户可以是用户或者用户ID 用户组可以是组名或id。文件名可以使由空格分开的文件列表，
在文件中可以包含通配符

> 只有文件主和超级用户才可以使用该命令

## 1.语法：chown(选项)（参数）
---
```
-c或——changes：效果类似“-v”参数，但仅回报更改的部分；
-f或--quite或——silent：不显示错误信息；
-h或--no-dereference：只对符号连接的文件作修改，而不更改其他任何相关文件；
-R或——recursive：递归处理，将指定目录下的所有文件及子目录一并处理；
-v或——version：显示指令执行过程；
--dereference：效果和“-h”参数相同；
--help：在线帮助；
--reference=<参考文件或目录>：把指定文件或目录的拥有者与所属群组全部设成和参考文件或目录的拥有者与所属群组相同；
--version：显示版本信息。
```

用户：组：指定所有者和所属工作组。当省略“：组”，仅改变文件所有者；
文件：指定要改变所有者和工作组的文件列表。支持多个文件和目标，支持shell通配符。


## 2.实例
```
将目录/usr/meng及其下面的所有文件、子目录的文件主改成 liu：

chown -R liu /usr/meng
```



# chmod


chmod命令：用来变更文件或者目录的权限。

在UNIX系统中，文件或者目录权限的控制分为以读取，写入，执行3种一般权限来区分，另有3种特殊权限可供运用，用户可以使用chmod指令去变更文件与目录的权限，设置方式采用文字或者数字皆可，以符号连接的权限无法变更，如果用户对符号链接修改权限，其改变会作用在被连接的原始文件。

## 1.权限范围的表示法如下：

```
u User，即文件或目录的拥有者；
g Group，即文件或目录的所属群组；
o Other，除了文件或目录拥有者或所属群组之外，其他用户皆属于这个范围；
a All，即全部的用户，包含拥有者，所属群组以及其他用户；
r 读取权限，数字代号为“4”;
w 写入权限，数字代号为“2”；
x 执行或切换权限，数字代号为“1”；
- 不具任何权限，数字代号为“0”；
s 特殊功能说明：变更文件或目录的权限。
```


## 2.语法:   chmod(选项)(参数)


```
选项
-c或——changes：效果类似“-v”参数，但仅回报更改的部分；
-f或--quiet或——silent：不显示错误信息；
-R或——recursive：递归处理，将指令目录下的所有文件及子目录一并处理；
-v或——verbose：显示指令执行过程；
--reference=<参考文件或目录>：把指定文件或目录的所属群组全部设成和参考文件或目录的所属群组相同；
<权限范围>+<权限设置>：开启权限范围的文件或目录的该选项权限设置；
<权限范围>-<权限设置>：关闭权限范围的文件或目录的该选项权限设置；
<权限范围>=<权限设置>：指定权限范围的文件或目录的该选项权限设置；


参数
  权限模式：指定文件的权限模式；
  文件：要改变权限的文件。
```


## 3、知识扩展和实例

Linux用 户分为：拥有者、组群(Group)、其他（other），Linux系统中，预设的情況下，系统中所有的帐号与一般身份使用者，以及root的相关信息， 都是记录在/etc/passwd文件中。每个人的密码则是记录在/etc/shadow文件下。 此外，所有的组群名称记录在/etc/group內！

```
linux文件的用户权限的分析图
例：rwx　rw-　r--

r=读取属性　　//值＝4
w=写入属性　　//值＝2
x=执行属性　　//值＝1

chmod u+x,g+w f01　　//为文件f01设置自己可以执行，组员可以写入的权限
chmod u=rwx,g=rw,o=r f01
chmod 764 f01
chmod a+x f01　　//对文件f01的u,g,o都设置可执行属性

文件的属主和属组属性设置

chown user:market f01　　//把文件f01给uesr，添加到market组
ll -d f1  查看目录f1的属性





#chmod –R 777 * 
r表示read w表示write x表示execute 读写运行三项可以用数字表示，就是r=4 w=2 x=1 
777 代表rwxrwxrwx 意思就是登陆用户（可以用命令id查看），及用户所在的组和其他人都有最高权限。

#chmod –R 777 * :
参数-R : 对目前目录下的所有档案与子目录进行相同的权限变更(即以递回的方式逐个变更) 

*：通配符，指当前目录下的所有文件及目录

将当前目录下的所有文件及子目录的文件拥有者权限设置为读、写、可执行，文件拥有者所在的用户组成员具备读、写、可执行权限，其它用户也具备读、写、可执行权限

 ```
 
 # 查看负载
 
 ## 使用uptime
 
 ```
 root@ecs-x-medium-2-linux-20200312105337:~# uptime
 14:33:47 up 23 days, 21:12,  1 user,  load average: 0.08, 0.02, 0.01
```
 - 使用top命令查看负载，在top下按“1”查看CPU核心数量，shift+"c"按cpu使用率大小排序，shif+"p"按内存使用率高低排序；
 - 使用iostat -x 命令来监控io的输入输出是否过大


# 查找文件的命令

http://www.ruanyifeng.com/blog/2009/10/5_ways_to_search_for_files_using_the_terminal.html

# ar命令：创建静态库.a文件


## 创建静态库
```
ar rcs my_lib.a xx1.o xx2.o 

- 参数r: 在库中插入模块(替换)，当插入的模块在库中存在，则替换同名模块. (replace)
- 参数c  创建一个库，不管库是否存在，都将创建.
- 参数s  创建目标索引文件，这在创建较大的库时能加快时间.
```
## 查看静态库
```
- ar t libxxx.a   //显示库中有哪些目标文件，只显示名称

- at tv libxxx.a   //显示库中有哪些目标文件，显示名称、时间、大小等信息

- nm -s libxxx.a   //显示库文件中的索引表

- ranlib libxxx.a  //为库文件创建索引表
```


# 实现linux下的tail命令
- 使用SEEK_END找到文件大小
- 然后使用SEEK_SET定位到最后一个page,从上面这个case看，page的大小是8192，如果最后一个page没满8192，seek到文件大小- 文件大小%8192
- 然后读取这个page的所有数据，统计换行的个数
- 如果不到n行，继续往前seek,直到取到n行数据
- 顺序读取数据，输出n行数据




# 正则表达式

- ^表示一行的开头，如/^#/以#开头的匹配
- $表示一行的结尾，如/}$/表示以}结尾的匹配
- \<表示词首，如：\<abc表示以abc为首的词
- \>表示词尾，如：abC\>表示以abc为结尾的词
- .表示任意单个字符
- *表示某个字符出现0次或多次

> https://coolshell.cn/articles/9104.html

# sed替换文件内容 stream editor 
- sed是一种流编辑器，是文本处理中非常重要的工具，能够完美配合正则表达式使用.
- 处理时，把当前处理的行存储在临时缓冲区中，称为模式空间（pattern space），接着用sed命令处理缓冲区中的内容，处理完成后，把缓冲区的内容送往屏幕，接着处理下一行，这样不断重复，直到文件末尾。



> -n是取消输出缓冲区中的文件，不然会先将缓冲区的文本输出

```
sed -n '1,$ p' test.txt   //输出从1到最后一行的文本
```




> -s 表示将book替换为books，并打印替换的行

```
sed -n 's/book/books/p' test.txt  //并不替换文件中的内容

//g是全局匹配
//十进制n 替换文本行中第n个匹配的字符串
//p 替换第一个匹配的字符串，并将缓冲区输出到标准磁盘
// w 替换第一个匹配的字符串，并且将改动的行输出的磁盘文件中

```

> -i 选项是重定向到文件内，sed命令处理只改变缓冲区文本的副本































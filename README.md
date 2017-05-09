# streamsetsfiletail-hack
streamsets的filetail插件有几个小问题
1、当采集日志所在目录中出现子目录的时候，采集会出现问题，因为livefile 的refresh方法没有将目录排除掉。
2、当正在采集的日志被挪走或者删除掉，即在本目录扫描不到的时候就会报错，或者采集没有进度，重启也会失败，这是因为reader没有release掉，没有触发重新扫描新的文件。

-rw-r--r-- 1 root root  6349 May  9 18:11 BaseFileContextProvider.class
-rw-r--r-- 1 root root  8983 May  9 18:11 FileContext.class
-rw-r--r-- 1 root root  7376 May  9 10:54 LiveFile.class
-rw-r--r-- 1 root root 10093 May  9 17:38 SingleLineLiveFileReader.class
-rw-r--r-- 1 root root  2137 May  9 15:13 FileContextProviderUtil.class


通过修改这些文件，替换掉streamsets-libs/streamsets-datacollector-basic-lib/lib/streamsets-datacollector-commonlib-2.4.0.0.jar中对应jar文件即可解决这个bug

对比解析Java类文件前后修改状态：

      实现ParserAbstract抽象类，重写modify、delOrAdd方法。
      给出了两种解析方法，分别是BinParser和SeParser类，后面需要进一步优化可以直接实现ParserAbstract抽象类。
      
      传入的数据类型为map对象，其中map对象包含以下内容
            map.put("fromFileName","");//原文件名
            map.put("toFileName","");//当前文件名
            map.put("fromFile","");//原文件内容
            map.put("toFile","");//当前文件内容
            map.put("fileType","");//文件类型（新增／删除／修改）
            map.put("delLines",new ArrayList());//删除的所有行号
            map.put("addLines",new ArrayList());//新增的所有行号
            
      输出一个hashMap对象，其中hashMap对象包含以下内容
            map.put("packageName","");
            map.put("methods",new ArrayList<MethodEntity>());
            MethodEntity类中包含，方法名称name、方法起始行beginIndex、方法结束行endIndex三个参数。
            
      例子：
          ParserAbstract parserAbstract = new BinParser();
          HashMap<String,Object> hashMap = parserAbstract.parserMd(map);
          

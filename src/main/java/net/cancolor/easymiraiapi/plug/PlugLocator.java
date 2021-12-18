package net.cancolor.easymiraiapi.plug;

import net.cancolor.easymiraiapi.utils.PlugLocatorCondition;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于加载插件
 */
@Component
@Conditional(PlugLocatorCondition.class)
public class PlugLocator implements ApplicationContextAware {
    /**
     * 用于保存插件实现类
     */
    //有条件限制的插件
    //事件,条件,插件
    private final Map<String, Map<String, PlugInterface>> conditionPlug = new HashMap<>();
    //事件,无条件限制插件
    private final Map<String, List<PlugInterface>> noConditionPlug = new HashMap<>();

    //总是执行的插件
    private final  List<PlugInterface> allMessagePlug=  new ArrayList<>();

    //没启动的插件
    private final List<PlugInterface> noStartPlug = new ArrayList<>();


    @Autowired
    PlugProperties plugProperties;


    /**
     * 获取应用上下文并获取相应的接口实现类
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //根据接口类型返回相应的所有bean
        Map<String, PlugInterface> implMap = applicationContext.getBeansOfType(PlugInterface.class);
        List<PlugProperties.PlugProp> plugPropList = plugProperties.getData();
        if (plugPropList != null) {
            for (PlugProperties.PlugProp plugProp : plugPropList) {
                String clzName = plugProp.getClzName();
                PlugInterface plugInterface = implMap.get(clzName);
                if (plugInterface != null) {
                    plugInterface.init()
                            .setName(plugProp.getName()).setClzName(plugProp.getClzName())
                            .setStatus(plugProp.getStatus()).setCondition(plugProp.getCondition())
                            .setConditionType(plugProp.getConditionType()).setEventList(plugProp.getEventList());
                    initPlugGroup(implMap, clzName, plugInterface);
                }
            }
        }
        for (Map.Entry<String, PlugInterface> entry : implMap.entrySet()) {
            String k = entry.getKey();
            PlugInterface v = entry.getValue();
            initPlugGroup(implMap, k, v);
        }

        implMap = null;
        plugProperties = null;
    }


    public void initPlugGroup(Map<String, PlugInterface> implMap, String clzName, PlugInterface plugInterface) {
        Plug plug = plugInterface.init();
        List<String> eventList = plug.getEventList();
        if (1 != plug.getStatus()) {
            noStartPlug.add(plugInterface);
        } else {
            allMessagePlug.add(plugInterface);
            for (String event : eventList) {
                if (2 == plug.getConditionType()) {
                    Map<String, PlugInterface> plugInterfaceMap = conditionPlug.get(event);
                    if (plugInterfaceMap == null) {
                        plugInterfaceMap = new HashMap<>();
                    }
                    plugInterfaceMap.put(plug.getCondition(), plugInterface);
                    conditionPlug.put(event, plugInterfaceMap);
                } else {
                    List<PlugInterface> noConditionPlugList = noConditionPlug.get(event);
                    if (noConditionPlugList == null) {
                        noConditionPlugList = new ArrayList<>();
                        noConditionPlug.put(event == null ? "All" : event, noConditionPlugList);
                    }
                    noConditionPlugList.add(plugInterface);
                }
            }
        }
        implMap.remove(clzName);
    }

    public Map<String, Map<String, PlugInterface>> getConditionPlug() {
        return conditionPlug;
    }

    public List<PlugInterface> getAllMessagePlug() {
        return allMessagePlug;
    }

    public Map<String, List<PlugInterface>> getNoConditionPlug() {
        return noConditionPlug;
    }

    public List<PlugInterface> getNoStartPlug() {
        return noStartPlug;
    }
}
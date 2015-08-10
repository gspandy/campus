package org.campus.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

class PropertiesConfiguration extends AbstractConfiguration {

    private Map<String, Object> store = new HashMap<String, Object>();

    public PropertiesConfiguration(Properties properties) {
        load(properties);
    }

    private void load(Properties properties) {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            addPropertyValues((String) entry.getKey(), entry.getValue(),
                    isDelimiterParsingDisabled() ? DISABLED_DELIMITER : getListDelimiter());
        }
    }

    @Override
    public Set<String> getKeys() {
        return store.keySet();
    }

    @Override
    public boolean containsKey(String key) {
        return store.containsKey(key);
    }

    @Override
    public Object getProperty(String key) {
        return store.get(key);
    }

    protected void addPropertyValues(String key, Object value, char delimiter) {
        Iterator<?> it = PropertyConverter.toIterator(value, delimiter);
        while (it.hasNext()) {
            addPropertyDirect(key, it.next());
        }
    }

    protected void addPropertyDirect(String key, Object value) {
        Object previousValue = getProperty(key);

        if (previousValue == null) {
            store.put(key, value);
        } else if (previousValue instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> valueList = (List<Object>) previousValue;
            valueList.add(value);
        } else {
            List<Object> list = new ArrayList<Object>();
            list.add(previousValue);
            list.add(value);

            store.put(key, list);
        }
    }
}

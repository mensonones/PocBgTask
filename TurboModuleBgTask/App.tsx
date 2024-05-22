import React, { useEffect, useState } from 'react';
import { SafeAreaView, StatusBar, StyleSheet, Text, View, ActivityIndicator } from 'react-native';
import RTNModuleTask from 'rtn-module-task/js/NativeModuleTask';

function App(): React.JSX.Element {
  const [result, setResult] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    fetchAndroidVersion();
    RTNModuleTask?.startTask()
    .then(() => {
      console.log('Task started');
    })
    .catch((error) => {
      console.error('Error starting task:', error);
    });
  }, []);

  const fetchAndroidVersion = async () => {
    try {
      const version = await RTNModuleTask?.getAndroidVersion();
      setResult(version ?? null);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching Android version:', error);
      setResult(null);
      setLoading(false);
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.content}>
        <Text style={styles.label}>
          Android version:
        </Text>
        {loading ? (
          <ActivityIndicator style={styles.activityIndicator} size="small" color="#0000ff" />
        ) : (
          <Text style={styles.text}>
            {result ?? 'Failed to fetch Android version'}
          </Text>
        )}
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  content: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  label: {
    marginLeft: 20,
    fontSize: 18,
    fontWeight: 'bold',
  },
  text: {
    marginLeft: 10,
    fontSize: 18,
  },
  activityIndicator: {
    marginLeft: 10,
  },
});

export default App;
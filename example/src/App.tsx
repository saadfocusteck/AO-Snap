import * as React from 'react';

import { StyleSheet, View, Text, Button, Alert } from 'react-native';
import AnalogoneSnap from 'react-native-analogone-snap';

export default function App() {
  const [os, setOS] = React.useState<string | undefined>();
  const [isGranted, setGranted] = React.useState<boolean | undefined>();
  const [constants, setConstants] = React.useState<object | undefined>();
  const [params, setParams] = React.useState<any | undefined>();

  React.useEffect(() => {
    setConstants(AnalogoneSnap.getConstants());
    AnalogoneSnap.getOS((value: string) => setOS(value));
    AnalogoneSnap.isCameraPermissionGranted().then(setGranted);
    AnalogoneSnap.multiParamFunc(1, 'Snap', { module: 'AnalogOne Snap' }).then(
      setParams
    );
  }, []);

  const onTakePicture = () => {
    if (isGranted == true) {
      AnalogoneSnap.takePicture();
    } else {
      Alert.alert(
        'Camera permission is granted. Please go to app settings and allow camera permission prior to using this feature.'
      );
    }
  };

  const ConstantsView = (props: any) => {
    const { constants } = props;
    return (
      <View>
        <Text>{JSON.stringify(constants, null, 3)}</Text>
      </View>
    );
  };

  const MultiParamsView = (props: any) => {
    const { params } = props;
    console.log(params);
    return (
      <View>
        {params != undefined ? (
          params.map((param: any) => {
            return (
              <View>
                <Text>_________________</Text>
                <Text>Value: {JSON.stringify(param, null, 3)}</Text>
                <Text>Type: {typeof param}</Text>
              </View>
            );
          })
        ) : (
          <Text>No params returned from native module</Text>
        )}
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <Text>Operating System (callback): {os}</Text>
      <Text>_______________________________________</Text>
      <Text>
        Camera Permission (promise):{' '}
        {isGranted == true ? 'Granted' : 'Not Granted'}
      </Text>
      <Text>_______________________________________</Text>
      <Text>Exported Constants (stringified):</Text>
      <ConstantsView constants={constants} />
      <Text>_______________________________________</Text>
      <Text>Multi param function (number, string, object)</Text>
      <MultiParamsView params={params} />
      <Text>_______________________________________</Text>
      <Button title="Take picture and crop" onPress={onTakePicture} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 50,
    marginBottom: 20,
    marginRight: 20,
    marginLeft: 20,
  },
});

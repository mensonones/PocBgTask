import type { TurboModule } from "react-native/Libraries/TurboModule/RCTExport";
import { TurboModuleRegistry } from "react-native";

export interface Spec extends TurboModule {
  getAndroidVersion(): Promise<string>;
  startTask(): Promise<string>;
  stopTask(): Promise<string>;
}

export default TurboModuleRegistry.get<Spec>("RTNModuleTask") as Spec | null;
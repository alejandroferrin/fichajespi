
import {
  DialogLayoutDisplay,
  ToastNotificationInitializer,
  ToastPositionEnum,
  ToastProgressBarEnum,
  ToastUserViewTypeEnum,
  ConfirmBoxInitializer,
  VerticalPosition
} from '@costlydeveloper/ngx-awesome-popup';

export class Popup {

  private static closeTime: number = 8000;
  private static textPosition: VerticalPosition = 'left';
  private static toastPosition: ToastPositionEnum = ToastPositionEnum.TOP_CENTER;

  static infoConfirmBox(title: string, msg: string, confirmBtn: string, denyBtn: string): ConfirmBoxInitializer {

    const confirmBox = new ConfirmBoxInitializer();
    confirmBox.setTitle(title);
    confirmBox.setMessage(msg);
    confirmBox.setButtonLabels(confirmBtn, denyBtn);

    // Choose layout color type
    confirmBox.setConfig({
      LayoutType: DialogLayoutDisplay.INFO // SUCCESS | INFO | NONE | DANGER | WARNING
    });
    return confirmBox;

  }

  static successConfirmBox(title: string, msg: string, confirmBtn: string, denyBtn: string): ConfirmBoxInitializer {

    const confirmBox = new ConfirmBoxInitializer();
    confirmBox.setTitle(title);
    confirmBox.setMessage(msg);
    confirmBox.setButtonLabels(confirmBtn, denyBtn);

    // Choose layout color type
    confirmBox.setConfig({
      LayoutType: DialogLayoutDisplay.SUCCESS // SUCCESS | INFO | NONE | DANGER | WARNING
    });
    return confirmBox;

  }

  static warningConfirmBox(title: string, msg: string, confirmBtn: string, denyBtn: string): ConfirmBoxInitializer {

    const confirmBox = new ConfirmBoxInitializer();
    confirmBox.setTitle(title);
    confirmBox.setMessage(msg);
    confirmBox.setButtonLabels(confirmBtn, denyBtn);

    // Choose layout color type
    confirmBox.setConfig({
      LayoutType: DialogLayoutDisplay.WARNING // SUCCESS | INFO | NONE | DANGER | WARNING
    });
    return confirmBox;

  }

  static dangerConfirmBox(title: string, msg: string, confirmBtn: string, denyBtn: string): ConfirmBoxInitializer {

    const confirmBox = new ConfirmBoxInitializer();
    confirmBox.setTitle(title);
    confirmBox.setMessage(msg);
    confirmBox.setButtonLabels(confirmBtn, denyBtn);

    // Choose layout color type
    confirmBox.setConfig({
      LayoutType: DialogLayoutDisplay.DANGER // SUCCESS | INFO | NONE | DANGER | WARNING
    });
    return confirmBox;

  }

  static toastSucess(title: string, msg: string) {
    const newToastNotification = new ToastNotificationInitializer();

    newToastNotification.setTitle(title);
    newToastNotification.setMessage(msg);

    // Choose layout color type
    newToastNotification.setConfig({
      AutoCloseDelay: this.closeTime, // optional
      TextPosition: this.textPosition, // optional
      LayoutType: DialogLayoutDisplay.SUCCESS, // SUCCESS | INFO | NONE | DANGER | WARNING
      ProgressBar: ToastProgressBarEnum.NONE, // INCREASE | DECREASE | NONE
      ToastUserViewType: ToastUserViewTypeEnum.STANDARD, // STANDARD | SIMPLE
      // TOP_LEFT | TOP_CENTER | TOP_RIGHT | TOP_FULL_WIDTH | BOTTOM_LEFT | BOTTOM_CENTER | BOTTOM_RIGHT | BOTTOM_FULL_WIDTH
      ToastPosition: this.toastPosition,
    });

    // Simply open the popup
    newToastNotification.openToastNotification$();
  }

  static toastDanger(title: string, msg: string) {
    const newToastNotification = new ToastNotificationInitializer();

    newToastNotification.setTitle(title);
    newToastNotification.setMessage(msg);

    // Choose layout color type
    newToastNotification.setConfig({
      AutoCloseDelay: this.closeTime, // optional
      TextPosition: this.textPosition, // optional
      LayoutType: DialogLayoutDisplay.DANGER, // SUCCESS | INFO | NONE | DANGER | WARNING
      ProgressBar: ToastProgressBarEnum.NONE, // INCREASE | DECREASE | NONE
      ToastUserViewType: ToastUserViewTypeEnum.STANDARD, // STANDARD | SIMPLE
      // TOP_LEFT | TOP_CENTER | TOP_RIGHT | TOP_FULL_WIDTH | BOTTOM_LEFT | BOTTOM_CENTER | BOTTOM_RIGHT | BOTTOM_FULL_WIDTH
      ToastPosition: this.toastPosition,
    });

    // Simply open the popup
    newToastNotification.openToastNotification$();
  }

  static toastWarning(title: string, msg: string) {
    const newToastNotification = new ToastNotificationInitializer();

    newToastNotification.setTitle(title);
    newToastNotification.setMessage(msg);

    // Choose layout color type
    newToastNotification.setConfig({
      AutoCloseDelay: this.closeTime, // optional
      TextPosition: this.textPosition, // optional
      LayoutType: DialogLayoutDisplay.WARNING, // SUCCESS | INFO | NONE | DANGER | WARNING
      ProgressBar: ToastProgressBarEnum.NONE, // INCREASE | DECREASE | NONE
      ToastUserViewType: ToastUserViewTypeEnum.STANDARD, // STANDARD | SIMPLE
      // TOP_LEFT | TOP_CENTER | TOP_RIGHT | TOP_FULL_WIDTH | BOTTOM_LEFT | BOTTOM_CENTER | BOTTOM_RIGHT | BOTTOM_FULL_WIDTH
      ToastPosition: this.toastPosition,
    });

    // Simply open the popup
    newToastNotification.openToastNotification$();
  }

  static toastINFO(title: string, msg: string) {
    const newToastNotification = new ToastNotificationInitializer();

    newToastNotification.setTitle(title);
    newToastNotification.setMessage(msg);

    // Choose layout color type
    newToastNotification.setConfig({
      AutoCloseDelay: this.closeTime, // optional
      TextPosition: this.textPosition, // optional
      LayoutType: DialogLayoutDisplay.INFO, // SUCCESS | INFO | NONE | DANGER | WARNING
      ProgressBar: ToastProgressBarEnum.NONE, // INCREASE | DECREASE | NONE
      ToastUserViewType: ToastUserViewTypeEnum.STANDARD, // STANDARD | SIMPLE
      // TOP_LEFT | TOP_CENTER | TOP_RIGHT | TOP_FULL_WIDTH | BOTTOM_LEFT | BOTTOM_CENTER | BOTTOM_RIGHT | BOTTOM_FULL_WIDTH
      ToastPosition: this.toastPosition,
    });

    // Simply open the popup
    newToastNotification.openToastNotification$();
  }
}
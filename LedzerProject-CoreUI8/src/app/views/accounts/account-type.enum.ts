export enum AccountType {
    SAVING =1 ,
    CURRENT = 2
}

export namespace AccountType {

    export function values() {
      return Object.keys(AccountType).filter(
        (type) => isNaN(<any>type) && type !== 'values'
      );
    }
  }

export class User {

  constructor(
      public id: number,
      public username: string,
      public password: string,
      public companyName: string,
      public nameOfContact: string,
      public email: string,
      public phoneNumber: string,
      public street: string,
      public city: string,
      public postalCode: string,
      public country: string,
      public creditsLeft: number,
      public creditsTotal: number,
      public authorities?: string[]
    ) {}
}

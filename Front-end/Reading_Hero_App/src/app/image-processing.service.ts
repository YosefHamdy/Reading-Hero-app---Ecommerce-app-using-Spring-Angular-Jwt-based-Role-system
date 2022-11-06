import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Book } from './common/book';
import { FileHandle } from './common/file-handle.model';

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor(private sanitizer: DomSanitizer) { }


  convertShowImages (book: Book){
    const bookImages: any[] = book.bookImages;


    const bookImageToFileHandle: FileHandle[] = [];

    for(let i = 0; i < bookImages.length; i++){

      const imageFileData = bookImages[i];

    const imageBlob =  this.dataBytesToBlob(imageFileData.picByte,imageFileData.type);

    const imageFile =  new File([imageBlob],imageFileData.name, { type:imageFileData.type});
  
    

        const finalFileHandle:FileHandle = {
          // this first file just akey from file model and second is the one we defined in 79 line
          file: imageFile,
          // create url from file
          url: this.sanitizer.bypassSecurityTrustUrl(
            window.URL.createObjectURL(imageFile)
          )
        };

        bookImageToFileHandle.push(finalFileHandle);
  }

    book.bookImages = bookImageToFileHandle;
    return book;
  }

  dataBytesToBlob(picByte: string, imageType: any){
   const byteString = window.atob(picByte);
   const arrayBuffer = new ArrayBuffer(byteString.length);

   const int8Array = new Uint8Array(arrayBuffer);

   for(let i = 0 ; i < byteString.length;i++){
      int8Array[i] = byteString.charCodeAt(i);

   }

   const blob = new Blob([int8Array], { type: imageType});
   return blob;
  }

}

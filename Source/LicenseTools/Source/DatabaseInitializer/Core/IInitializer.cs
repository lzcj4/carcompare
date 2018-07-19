using System;

namespace DatabaseInitializer
{
    interface IInitializer
    {
        event Action<string> ProgressChanged;

        void Initialize();
    }
}
